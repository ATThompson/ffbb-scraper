package fr.athompson.database.services.api;

import fr.athompson.database.entities.RencontreDB;
import fr.athompson.database.mappers.RencontreMapperDB;
import fr.athompson.database.repositories.RencontreRepository;
import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.Rencontre;
import fr.athompson.domain.services.spi.SPIChercherProchainMatchOrganisationCompetition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChercherProchainMatchOrganisationCompetition implements SPIChercherProchainMatchOrganisationCompetition {

    RencontreRepository rencontreRepository;

    RencontreMapperDB rencontreMapperDB;

    public Optional<Rencontre> chercher(String idOrganisation, Competition competition) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime prochainDimanche = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).withHour(23);
        Optional<RencontreDB> prochainMatch = rencontreRepository.findProchainMatch(idOrganisation, competition.idChampionnat(), competition.idDivision(), competition.idPoule(), prochainDimanche);
        return prochainMatch.map(rencontreMapperDB::toDomain);
    }
}
