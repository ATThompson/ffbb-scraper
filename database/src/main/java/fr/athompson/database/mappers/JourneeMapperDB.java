package fr.athompson.database.mappers;

import fr.athompson.database.entities.JourneeDB;
import fr.athompson.domain.entities.Journee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JourneeMapperDB {

    @Mapping(target = "competition", ignore = true)
    JourneeDB toDatabase(Journee journee);
}
