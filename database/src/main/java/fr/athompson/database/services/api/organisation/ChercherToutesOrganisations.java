package fr.athompson.database.services.api.organisation;

import fr.athompson.database.mappers.OrganisationMapperDB;
import fr.athompson.database.repositories.OrganisationRepository;
import fr.athompson.domain.entities.Organisation;
import fr.athompson.domain.services.spi.organisation.SPIChercherToutesOrganisations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChercherToutesOrganisations implements SPIChercherToutesOrganisations {
    OrganisationRepository organisationRepository;

    OrganisationMapperDB organisationMapper;

    @Override
    public List<Organisation> chercher() {
        return organisationRepository.findAll()
                .stream()
                .map(organisationMapper::toEntitySansEngagements)
                .toList();
    }
}
