package fr.athompson.database.mappers;

import fr.athompson.cron.entities.JourneeScrap;
import fr.athompson.database.entities.JourneeDB;
import fr.athompson.domain.entities.Journee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JourneeMapperDB {

    @Mapping(target = "competition", ignore = true)
    @Mapping(target = "journeeIdHtml", source = "idJournee")
    JourneeDB toDatabase(JourneeScrap journee);
}
