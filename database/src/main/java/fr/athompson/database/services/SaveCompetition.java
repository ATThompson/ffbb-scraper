package fr.athompson.database.services;

import fr.athompson.cron.spi.SPISaveCompetition;
import fr.athompson.database.entities.ClassementDB;
import fr.athompson.database.entities.CompetitionDB;
import fr.athompson.database.mappers.ClassementMapperDB;
import fr.athompson.database.mappers.CompetitionMapperDB;
import fr.athompson.database.repositories.ClassementRepository;
import fr.athompson.database.repositories.CompetitionRepository;
import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.classement.RowClassement;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class SaveCompetition implements SPISaveCompetition {

    CompetitionRepository competitionRepository;

    CompetitionMapperDB competitionMapperDB;

    ClassementMapperDB classementMapperDB;

    ClassementRepository classementRepository;

    @Transactional
    public void execute(Competition competition){
        Optional<CompetitionDB> optionalCompetitionPresenteEnBase = competitionRepository.findByOrganisationIdHtmlAndDivisionIdHtmlAndPouleIdHtml(
                competition.idOrganisation(),
                competition.idDivision(),
                competition.idPoule()
        );
        var classement = optionalCompetitionPresenteEnBase.get().getClassements();
        for(RowClassement rowClassement : competition.classement().rowsClassement()){
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
    }

}
