package fr.athompson.database.services;

import fr.athompson.cron.spi.SPISaveOrganisation;
import fr.athompson.database.entities.ClassementDB;
import fr.athompson.database.entities.EquipeDB;
import fr.athompson.database.entities.RencontreDB;
import fr.athompson.database.mappers.*;
import fr.athompson.database.repositories.*;
import fr.athompson.domain.entities.Equipe;
import fr.athompson.domain.entities.Journee;
import fr.athompson.domain.entities.Organisation;
import fr.athompson.domain.entities.Rencontre;
import fr.athompson.domain.entities.classement.RowClassement;
import fr.athompson.domain.entities.engagement.Engagement;
import fr.athompson.domain.entities.engagement.EngagementChampionnat;
import fr.athompson.domain.entities.engagement.EngagementPlateau;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void saveOrganisation(Organisation organisation) {
        var organisationDB = organisationRepository.findByNom(organisation.nom()).orElse(null);
        if(null == organisationDB) {
            //On enregistre tout
            organisationDB = organisationMapperDB.toDatabase(organisation);
            organisationRepository.save(organisationDB);
        }
        //Sinon faut update
        for(Engagement engagement : organisation.engagements()){
            //On cherche si la compétition existe ?
            //Si oui on continue
                //Est-ce qu'il existe dans la competition une équipe ayant l'identifiant de l'organisation?
                //Si oui
                    //Est-ce qu'il existe un engagement avec l'identifiant de la compétition et l'identifiant de l'équipe trouvée et la poule.
                    //Si oui, ne rien faire
                    //Si non, créer l'engagement et mettre l'identifiant de la compétition et identifiant de l'équipe dans l'engagement
                //Cas impossible
            //Si non => Créer la compétition, le classement, les rencontres , les équipes
                // Quand on tombe sur l'équipe ayant le même nom de l'organisation on l'insert dans l'engagement fraichement crée



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
            var competitionDBFound = competitionRepository.findByNiveauAndDivisionAndCategorieAndTypeAndSexeAndNombrePoulesAndAnneeAndComite_Id(
                    competitionDB.getNiveau(),
                    competitionDB.getDivision(),
                    competitionDB.getCategorie(),
                    type,competitionDB.getSexe(),
                    competitionDB.getNombrePoules(),
                    2025,
                    comiteDB.getId()).orElse(null);

            var equipeDBList = new ArrayList<EquipeDB>();
            Optional<EquipeDB> equipeEngagement;
            if(null == competitionDBFound) {
                competitionDB.setType(type);
                competitionDB.setAnnee(2025);
                competitionDB.setComite(comiteDB);
                competitionRepository.save(competitionDB);

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

                    for(Rencontre rencontre : journee.recontres()){
                        var rencontreDB = rencontreMapperDB.toDatabase(rencontre);
                        var equipeDBDomicile = equipeDBList.stream()
                                .filter(e ->
                                        e.getNom().equals(rencontre.equipeDomicile().nom())
                                                && e.getOrganisationIdHtml().equals(rencontre.equipeDomicile().idOrganisation())
                                ).findFirst();
                        rencontreDB.setEquipeDomicile(equipeDBDomicile.orElse(null));
                        var equipeDBVisiteur = equipeDBList.stream()
                                .filter(e ->
                                        e.getNom().equals(rencontre.equipeVisiteur().nom())
                                                && e.getOrganisationIdHtml().equals(rencontre.equipeVisiteur().idOrganisation())
                                ).findFirst();
                        rencontreDB.setEquipeVisiteur(equipeDBVisiteur.orElse(null));
                        rencontreDB.setJournee(journeeDB);
                        rencontreRepository.save(rencontreDB);
                    }
                }
                equipeEngagement = equipeDBList.stream()
                        .filter(e -> e.getOrganisationIdHtml().equals(organisation.idOrganisation())
                        ).findFirst();
            }else{
                competitionDB = competitionDBFound;
                equipeDBList = competitionDB.getClassements().stream().map(ClassementDB::getEquipe).collect(Collectors.toCollection(ArrayList::new));
                equipeEngagement = equipeDBList.stream()
                        .filter(e -> e.getOrganisationIdHtml().equals(organisation.idOrganisation()) && e.getEngagements().isEmpty()
                        ).findFirst();
            }

            var engagementDB = engagementMapperDB.toDatabase(engagement);
            engagementDB.setOrganisation(organisationDB);
            engagementDB.setCompetition(competitionDB);
            //Si plusieurs équipes récupérer leurs id db et faire un findBy et checker quelles ne sont pas reliés à un autre engagement sinon prendre l'autre

            engagementDB.setEquipe(equipeEngagement.orElse(null));
            engagementRepository.save(engagementDB);
        }
        // On recherche l'organisation.
    }
}
