package fr.athompson.database.services.organisation;

import fr.athompson.cron.entities.JourneeScrap;
import fr.athompson.cron.entities.OrganisationScrap;
import fr.athompson.cron.entities.RencontreScrap;
import fr.athompson.cron.entities.classement.RowClassementScrap;
import fr.athompson.cron.entities.engagement.EngagementScrap;
import fr.athompson.cron.entities.engagement.EngagementScrapChampionnat;
import fr.athompson.cron.entities.engagement.EngagementScrapPlateau;
import fr.athompson.cron.spi.SPISaveOrganisation;
import fr.athompson.database.entities.EquipeDB;
import fr.athompson.database.entities.JourneeDB;
import fr.athompson.database.entities.RencontreDB;
import fr.athompson.database.mappers.*;
import fr.athompson.database.repositories.*;
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
public class SaveOrganisation implements SPISaveOrganisation {

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
    public void saveOrganisation(OrganisationScrap organisation) {
        var organisationDB = organisationRepository.findByNom(organisation.nom()).orElse(null);
        if(null == organisationDB) {
            organisationDB = organisationMapperDB.toDatabase(organisation);
            organisationRepository.save(organisationDB);
        }
        for(EngagementScrap engagement : organisation.engagements()){

            var competition = engagement.getCompetitionEngagee();
            var comiteDB = comiteRepository.findByComiteIdHtml(competition.comite().idComite()).orElse(null);
            if(null == comiteDB) {
                comiteDB = comiteMapperDB.toDatabase(competition.comite());
                comiteRepository.save(comiteDB);
            }

            String type;
            if(engagement instanceof EngagementScrapChampionnat)
                type = "CHAMPIONNAT";
            else if (engagement instanceof EngagementScrapPlateau) {
                type = "PLATEAU";
            }else
                type = "COUPE";
            var competitionDB = competitionMapperDB.toDatabase(competition);

            var competitionDBFound = competitionRepository.findByOrganisationIdHtmlAndDivisionIdHtmlAndPouleIdHtml(
                    competitionDB.getOrganisationIdHtml(),
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
                for(RowClassementScrap rowClassement : competition.classement().rowsClassement()){
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
                for(JourneeScrap journee : competition.journees()){
                    var journeeDB = journeeMapperDB.toDatabase(journee);
                    journeeDB.setCompetition(competitionDB);
                    journeeRepository.save(journeeDB);

                    for(RencontreScrap rencontre : journee.getRecontres()){
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
