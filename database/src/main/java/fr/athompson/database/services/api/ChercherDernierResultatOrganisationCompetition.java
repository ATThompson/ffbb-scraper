package fr.athompson.database.services.api;

import fr.athompson.database.entities.RencontreDB;
import fr.athompson.database.mappers.RencontreMapperDB;
import fr.athompson.database.repositories.RencontreRepository;
import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.entities.Rencontre;
import fr.athompson.domain.services.spi.SPIChercherDernierResultatOrganisationCompetition;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChercherDernierResultatOrganisationCompetition implements SPIChercherDernierResultatOrganisationCompetition {

    RencontreRepository rencontreRepository;

    RencontreMapperDB rencontreMapperDB;

    public Optional<Rencontre> chercher(String idOrganisation, Competition competition) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dernierVendredi = now.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)).withHour(1);
        LocalDateTime prochainJeudi = now.with(TemporalAdjusters.next(DayOfWeek.THURSDAY)).withHour(23);

        Optional<RencontreDB> dernierResultat = rencontreRepository.findDernierResultat(idOrganisation, competition.idChampionnat(), competition.idDivision(), competition.idPoule(), dernierVendredi, prochainJeudi);

        return dernierResultat.map(rencontreMapperDB::toDomain);
    }
}
