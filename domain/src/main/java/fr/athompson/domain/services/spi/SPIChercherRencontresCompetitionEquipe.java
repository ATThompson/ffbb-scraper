package fr.athompson.domain.services.spi;

import fr.athompson.domain.entities.Rencontre;

import java.util.List;

public interface SPIChercherRencontresCompetitionEquipe {
    public List<Rencontre> chercher(String idOrganisation, String s, String s1, String s2);
}
