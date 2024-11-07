package fr.athompson.database.services.competition;

import fr.athompson.cron.entities.CompetitionScrap;
import fr.athompson.cron.entities.JourneeScrap;
import fr.athompson.cron.entities.RencontreScrap;
import fr.athompson.cron.entities.classement.RowClassementScrap;
import fr.athompson.cron.spi.SPISaveCompetition;
import fr.athompson.database.entities.ClassementDB;
import fr.athompson.database.entities.CompetitionDB;
import fr.athompson.database.entities.RencontreDB;
import fr.athompson.database.mappers.ClassementMapperDB;
import fr.athompson.database.mappers.RencontreMapperDB;
import fr.athompson.database.repositories.ClassementRepository;
import fr.athompson.database.repositories.CompetitionRepository;
import fr.athompson.database.repositories.RencontreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SaveCompetition implements SPISaveCompetition {


    CompetitionRepository competitionRepository;

    ClassementMapperDB classementMapperDB;

    ClassementRepository classementRepository;

    RencontreRepository rencontreRepository;

    RencontreMapperDB rencontreMapperDB;

    @Transactional
    public void execute(CompetitionScrap competition) {
        Optional<CompetitionDB> optionalCompetitionPresenteEnBase = competitionRepository.findByOrganisationIdHtmlAndDivisionIdHtmlAndPouleIdHtml(
                competition.idOrganisation(),
                competition.idDivision(),
                competition.idPoule()
        );
        if (optionalCompetitionPresenteEnBase.isPresent()) {
            var competitionDB = optionalCompetitionPresenteEnBase.get();
            var classement = competitionDB.getClassements();
            for (RowClassementScrap rowClassement : competition.classement().rowsClassement()) {
                ClassementDB ligneClassementDejaPresenteEnBase = classement.stream()
                        .filter(equipeDB ->
                                equipeDB.getEquipe().getNom().equals(rowClassement.equipe().nom())
                                        && equipeDB.getEquipe().getOrganisationIdHtml().equals(rowClassement.equipe().idOrganisation())).findFirst().get();

                var classementDBToSave = classementMapperDB.toDatabase(rowClassement);

                classementDBToSave.setEquipe(ligneClassementDejaPresenteEnBase.getEquipe());
                classementDBToSave.setCompetition(ligneClassementDejaPresenteEnBase.getCompetition());
                classementDBToSave.setId(ligneClassementDejaPresenteEnBase.getId());

                classementRepository.save(classementDBToSave);
            }

            for (JourneeScrap journee : competition.journees()) {
                List<RencontreDB> rencontresDB = rencontreRepository.findByJournee_journeeIdHtmlAndJournee_Competition_id(journee.getIdJournee(), competitionDB.getId());
                for (RencontreDB rencontreDB : rencontresDB) {
                    RencontreScrap rencontreScrap = journee.getRecontres().stream().filter(j -> j.numeroRencontre().equals(rencontreDB.getRencontreIdHtml())).findFirst().orElse(null);
                    var rencontreDBToSave = rencontreMapperDB.toDatabase(rencontreScrap);
                    rencontreDBToSave.setJournee(rencontreDB.getJournee());
                    rencontreDBToSave.setId(rencontreDB.getId());
                    rencontreDBToSave.setEquipeVisiteur(rencontreDB.getEquipeVisiteur());
                    rencontreDBToSave.setEquipeDomicile(rencontreDB.getEquipeDomicile());
                    rencontreRepository.save(rencontreDBToSave);
                }
            }
        }
    }

}
