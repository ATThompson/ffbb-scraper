package fr.athompson.database.mappers;

import fr.athompson.database.entities.JourneeDB;
import fr.athompson.domain.entities.Journee;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {RencontreMapperDB.class})
public interface JourneeMapperDB {

    @Mapping(target = "competition", ignore = true)
    @Mapping(target = "journeeIdHtml", source = "idJournee")
    JourneeDB toDatabase(Journee journee);

    @Mapping(target = "idJournee", source = "journeeIdHtml")
    Journee toDomain(JourneeDB journeeDB);
}
