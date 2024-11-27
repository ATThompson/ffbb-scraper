package fr.athompson.database.services.api.organisation;


import fr.athompson.database.entities.EquipeDB;
import fr.athompson.database.entities.JourneeDB;
import fr.athompson.database.entities.RencontreDB;
import fr.athompson.database.mappers.*;
import fr.athompson.database.repositories.*;
import fr.athompson.domain.entities.Journee;
import fr.athompson.domain.entities.Organisation;
import fr.athompson.domain.entities.Rencontre;
import fr.athompson.domain.entities.classement.RowClassement;
import fr.athompson.domain.entities.engagement.Engagement;
import fr.athompson.domain.entities.engagement.EngagementChampionnat;
import fr.athompson.domain.entities.engagement.EngagementPlateau;
import fr.athompson.domain.services.spi.organisation.SPISauvegarderOrganisation;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class SauvegarderOrganisation implements SPISauvegarderOrganisation {

    OrganisationRepository organisationRepository;

    EngagementRepository engagementRepository;

    EquipeRepository equipeRepository;

    CompetitionRepository competitionRepository;

    ClassementRepository classementRepository;

    JourneeRepository journeeRepository;

    RencontreRepository rencontreRepository;

    OrganisationMapperDB organisationMapperDB;

    EquipeMapperDB equipeMapperDB;

    ClassementMapperDB classementMapperDB;

    CompetitionMapperDB competitionMapperDB;

    JourneeMapperDB journeeMapperDB;

    RencontreMapperDB rencontreMapperDB;

    EngagementMapperDB engagementMapperDB;

    ComiteMapperDB comiteMapperDB;

    ComiteRepository comiteRepository;
    @Override
    @Transactional
    public void sauvegarder(Organisation organisation) {
        var organisationDB = organisationRepository.findByNom(organisation.nom()).orElse(null);
        if(null == organisationDB) {
            organisationDB = organisationMapperDB.toDatabase(organisation);
            organisationRepository.save(organisationDB);
        }
        for(Engagement engagement : organisation.engagements()){

            var competition = engagement.getCompetitionEngagee();
            var comiteDB = comiteRepository.findByComiteIdHtml(competition.comite().idComite()).orElse(null);
            if(null == comiteDB) {
                comiteDB = comiteMapperDB.toDatabase(competition.comite());
                comiteRepository.save(comiteDB);
            }

            String type;
            if(engagement instanceof EngagementChampionnat)
                type = "CHAMPIONNAT";
            else if (engagement instanceof EngagementPlateau) {
                type = "PLATEAU";
            }else
                type = "COUPE";
            var competitionDB = competitionMapperDB.toDatabase(competition);

            var competitionDBFound = competitionRepository.findByChampionnatIdHtmlAndDivisionIdHtmlAndPouleIdHtml(
                    competitionDB.getChampionnatIdHtml(),
                    competitionDB.getDivisionIdHtml(),
                    competitionDB.getPouleIdHtml()
            );
            List<EquipeDB> equipeDBList;
            Optional<EquipeDB> equipeEngagement;
            if(competitionDBFound.isEmpty()) {
                competitionDB.setType(type);
                //TODO: Modifier ca
                competitionDB.setAnnee(2025);
                competitionDB.setComite(comiteDB);
                competitionRepository.save(competitionDB);
                equipeDBList = new ArrayList<>();
                //Si la competition a été trouvée
                for(RowClassement rowClassement : competition.classement().rowsClassement()){
                    //Pour chaque ligne de classement
                    //Retrouver l'équipe ?
                    //Si oui cool
                    //Sinon la créer
                    var equipeDB = equipeMapperDB.toDatabase(rowClassement.equipe());
                    equipeRepository.save(equipeDB);
                    equipeDBList.add(equipeDB);
                    var classement = classementMapperDB.toDatabase(rowClassement);
                    classement.setEquipe(equipeDB);
                    classement.setCompetition(competitionDB);
                    classementRepository.save(classement);
                }
                for(Journee journee : competition.journees()){
                    var journeeDB = journeeMapperDB.toDatabase(journee);
                    journeeDB.setCompetition(competitionDB);
                    journeeRepository.save(journeeDB);

                    for(Rencontre rencontre : journee.rencontres()){
                        var rencontreDB = rencontreMapperDB.toDatabase(rencontre);
                        var equipeDBDomicile = equipeDBList.stream()
                                .filter(e ->
                                        e.getNom().equals(rencontre.equipeDomicile().nom())
                                                && e.getOrganisationIdHtml().equals(rencontre.equipeDomicile().idOrganisation())
                                ).findFirst().orElse(null);
                        if(null == equipeDBDomicile) {
                            equipeDBDomicile = equipeMapperDB.toDatabase(rencontre.equipeDomicile());
                            equipeRepository.save(equipeDBDomicile);
                            equipeDBList.add(equipeDBDomicile);
                        }
                        rencontreDB.setEquipeDomicile(equipeDBDomicile);
                        var equipeDBVisiteur = equipeDBList.stream()
                                .filter(e ->
                                        e.getNom().equals(rencontre.equipeVisiteur().nom())
                                                && e.getOrganisationIdHtml().equals(rencontre.equipeVisiteur().idOrganisation())
                                ).findFirst().orElse(null);
                        if(null == equipeDBVisiteur) {
                            equipeDBVisiteur = equipeMapperDB.toDatabase(rencontre.equipeVisiteur());
                            equipeRepository.save(equipeDBVisiteur);
                            equipeDBList.add(equipeDBVisiteur);
                        }
                        rencontreDB.setEquipeVisiteur(equipeDBVisiteur);
                        rencontreDB.setJournee(journeeDB);
                        rencontreRepository.save(rencontreDB);
                    }
                }
                equipeEngagement = equipeDBList.stream()
                        .filter(e -> e.getOrganisationIdHtml().equals(organisation.idOrganisation())
                        ).findFirst();
            }else{
                competitionDB = competitionDBFound.get();
                equipeDBList = competitionDB.getJournees().stream().map(JourneeDB::getRencontres).flatMap(Collection::stream).map(RencontreDB::getEquipeDomicile).distinct().toList();
                equipeEngagement = equipeDBList.stream()
                        .filter(e -> e.getOrganisationIdHtml().equals(organisation.idOrganisation()) && e.getEngagements().isEmpty()
                        ).findFirst();
            }

            var engagementDB = engagementMapperDB.toDatabase(engagement);
            engagementDB.setOrganisation(organisationDB);
            engagementDB.setCompetition(competitionDB);
            if(equipeEngagement.isEmpty()){
                //TODO: Pas de nouvelles équipes à engager
                log.info("PAS DE NOUVEAUX ENGAGEMENTS pour l'organisation "+organisation.nom());
                return;
            }
            engagementDB.setEquipe(equipeEngagement.orElse(null));
            engagementRepository.save(engagementDB);
        }
    }
}
