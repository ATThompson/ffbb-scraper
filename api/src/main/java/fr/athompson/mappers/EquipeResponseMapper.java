package fr.athompson.mappers;

import fr.athompson.domain.entities.Equipe;
import fr.athompson.entities.EquipeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EquipeResponseMapper {

    @Mapping(target = "identifiantOrganisation", source = "idOrganisation")
    EquipeResponse toResponse(Equipe equipe);
}
