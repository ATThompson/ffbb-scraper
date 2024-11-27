package fr.athompson.database.mappers;

import fr.athompson.database.entities.ClassementDB;
import fr.athompson.domain.entities.classement.RowClassement;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {EquipeMapperDB.class})
public interface ClassementMapperDB {

    @Mapping(target = "competition", ignore = true)
    @Mapping(target = "equipe", ignore = true)
    ClassementDB toDatabase(RowClassement rowClassement);

}
