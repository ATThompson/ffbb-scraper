package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.Organisation;
import fr.athompson.scrap.entities.OrganisationScrap;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = EngagementMapper.class)
public interface OrganisationMapper {
    Organisation toDomain(OrganisationScrap organisationScrap);
}
