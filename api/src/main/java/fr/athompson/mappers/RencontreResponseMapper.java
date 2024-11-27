package fr.athompson.mappers;

import fr.athompson.domain.entities.Rencontre;
import fr.athompson.entities.RencontreResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {EquipeResponseMapper.class})
public interface RencontreResponseMapper {

    RencontreResponse toResponse(Rencontre rencontre);

    List<RencontreResponse> toResponse(List<Rencontre> rencontres);
}
