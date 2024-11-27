package fr.athompson.mappers;


import fr.athompson.domain.entities.DernierResultat;
import fr.athompson.entities.DernierResultatResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {CompetitionResponseMapper.class, RencontreResponseMapper.class})
public interface DernierResultatResponseMapper {
    DernierResultatResponse toResponse(DernierResultat dernierResultat);

    List<DernierResultatResponse> toResponse(List<DernierResultat> dernierResultats);
}
