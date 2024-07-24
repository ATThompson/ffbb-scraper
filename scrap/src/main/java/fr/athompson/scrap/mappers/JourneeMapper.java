package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.Journee;
import fr.athompson.scrap.entities.JourneeScrap;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RencontreMapper.class)
public interface JourneeMapper {
    Journee toDomain(JourneeScrap journeeScrap);
}
