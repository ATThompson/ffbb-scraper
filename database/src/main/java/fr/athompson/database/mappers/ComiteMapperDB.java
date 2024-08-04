package fr.athompson.database.mappers;

import fr.athompson.database.entities.ComiteDB;
import fr.athompson.domain.entities.Comite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComiteMapperDB {
    @Mapping(target = "comiteIdHtml", source = "idComite")
    ComiteDB toDatabase(Comite comite);

}
