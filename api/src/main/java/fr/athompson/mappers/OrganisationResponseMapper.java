package fr.athompson.mappers;

import fr.athompson.domain.entities.Organisation;
import fr.athompson.entities.OrganisationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrganisationResponseMapper {

    @Mapping(target = "identifiant", source = "idOrganisation")
    OrganisationResponse toResponse(Organisation organisation);

}
