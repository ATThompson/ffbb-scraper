package fr.athompson.mappers;

import fr.athompson.domain.entities.ProchainMatch;
import fr.athompson.entities.ProchainMatchResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {CompetitionResponseMapper.class, RencontreResponseMapper.class})

public interface ProchainMatchResponseMapper {

    ProchainMatchResponse toResponse(ProchainMatch prochainMatch);

    List<ProchainMatchResponse> toResponse(List<ProchainMatch> prochainsMatchs);
}
