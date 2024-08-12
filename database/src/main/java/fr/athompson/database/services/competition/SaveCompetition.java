package fr.athompson.database.services.competition;

import fr.athompson.cron.spi.SPISaveCompetition;
import fr.athompson.database.entities.ClassementDB;
import fr.athompson.database.entities.CompetitionDB;
import fr.athompson.database.entities.RencontreDB;
import fr.athompson.database.mappers.ClassementMapperDB;
import fr.athompson.database.mappers.CompetitionMapperDB;
import fr.athompson.database.mappers.RencontreMapperDB;
import fr.athompson.database.repositories.ClassementRepository;
import fr.athompson.database.repositories.CompetitionRepository;
import fr.athompson.database.repositories.RencontreRepository;
import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.Journee;
import fr.athompson.domain.entities.Rencontre;
import fr.athompson.domain.entities.classement.RowClassement;
import fr.athompson.scrap.mappers.RencontreMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class SaveCompetition implements SPISaveCompetition {

    private final RencontreMapper rencontreMapper;
    CompetitionRepository competitionRepository;

    CompetitionMapperDB competitionMapperDB;

    ClassementMapperDB classementMapperDB;

    ClassementRepository classementRepository;

    RencontreRepository rencontreRepository;

    RencontreMapperDB rencontreMapperDB;

    @Transactional
    public void execute(Competition competition) {
        Optional<CompetitionDB> optionalCompetitionPresenteEnBase = competitionRepository.findByOrganisationIdHtmlAndDivisionIdHtmlAndPouleIdHtml(
                competition.idOrganisation(),
                competition.idDivision(),
                competition.idPoule()
        );
        if (optionalCompetitionPresenteEnBase.isPresent()) {
            var competitionDB = optionalCompetitionPresenteEnBase.get();
            var classement = competitionDB.getClassements();
            for (RowClassement rowClassement : competition.classement().rowsClassement()) {
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

            for (Journee journee : competition.journees()) {
                List<RencontreDB> rencontresDB = rencontreRepository.findByJournee_journeeIdHtmlAndJournee_Competition_id(journee.idJournee(), competitionDB.getId());
                for (RencontreDB rencontreDB : rencontresDB) {
                    Rencontre rencontreDomain = journee.recontres().stream().filter(j -> j.numeroRencontre().equals(rencontreDB.getRencontreIdHtml())).findFirst().orElse(null);
                    var rencontreDBToSave = rencontreMapperDB.toDatabase(rencontreDomain);
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
