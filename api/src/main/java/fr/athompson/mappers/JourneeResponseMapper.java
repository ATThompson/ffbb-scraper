package fr.athompson.mappers;

import fr.athompson.domain.entities.Journee;
import fr.athompson.entities.JourneeResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {RencontreResponseMapper.class})
public interface JourneeResponseMapper {

    @Mapping(target = "numeroJournee", source = "idJournee")
    JourneeResponse toResponse(Journee journee);

    List<JourneeResponse> toResponse(List<Journee> journees);
}
