package fr.athompson.database.services.api;

import fr.athompson.database.entities.RencontreDB;
import fr.athompson.database.mappers.RencontreMapperDB;
import fr.athompson.database.repositories.RencontreRepository;
import fr.athompson.domain.entities.Rencontre;
import fr.athompson.domain.services.spi.SPIChercherRencontresCompetitionEquipe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChercherRencontresCompetitionEquipe implements SPIChercherRencontresCompetitionEquipe {

    RencontreRepository rencontreRepository;

    RencontreMapperDB rencontreMapperDB;

    public List<Rencontre> chercher(String idOrganisation, String idChampionnat, String idDivision, String idPoule ){

         List<RencontreDB> rencontresDB = rencontreRepository.findAllByCompetitionAndEquipe(idOrganisation, idChampionnat, idDivision, idPoule);

         return rencontreMapperDB.toDomain(rencontresDB);
    }
}
