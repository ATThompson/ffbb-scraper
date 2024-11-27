package fr.athompson.mappers;

import fr.athompson.domain.entities.Competition;
import fr.athompson.entities.CompetitionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompetitionResponseMapper {

    @Mapping(target = "identifiantChampionnat", source = "idChampionnat")
    @Mapping(target = "identifiantDivision", source = "idDivision")
    @Mapping(target = "identifiantPoule", source = "idPoule")
    CompetitionResponse toResponse(Competition competition);
}
