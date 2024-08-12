package fr.athompson.database.services.organisation;

import fr.athompson.database.mappers.OrganisationMapperDB;
import fr.athompson.database.repositories.OrganisationRepository;
import fr.athompson.domain.entities.Organisation;
import fr.athompson.domain.services.organisation.SPIGetAllOrganisations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AllOrganisations implements SPIGetAllOrganisations {
    OrganisationRepository organisationRepository;

    OrganisationMapperDB organisationMapper;

    @Override
    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll().stream().map(organisationMapper::toEntitySansEngagements).toList();
    }
}
