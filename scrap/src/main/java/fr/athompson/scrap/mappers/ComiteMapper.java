package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.Comite;
import fr.athompson.scrap.entities.ComiteScrap;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComiteMapper {
    Comite toDomain(ComiteScrap comiteScrap);
}
