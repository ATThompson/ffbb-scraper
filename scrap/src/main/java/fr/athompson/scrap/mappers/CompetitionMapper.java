package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.Competition;
import fr.athompson.scrap.entities.CompetitionScrap;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ClassementMapper.class, JourneeMapper.class, EquipeMapper.class})
public interface CompetitionMapper {
    Competition toDomain(CompetitionScrap competitionScrap);
}
