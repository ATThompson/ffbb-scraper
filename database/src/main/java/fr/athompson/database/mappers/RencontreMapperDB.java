package fr.athompson.database.mappers;

import fr.athompson.database.entities.RencontreDB;
import fr.athompson.domain.entities.Rencontre;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {EquipeMapperDB.class})
public interface RencontreMapperDB {

    @Mapping(target = "equipeDomicile", ignore = true)
    @Mapping(target = "equipeVisiteur", ignore = true)
    @Mapping(target = "journee", ignore = true)
    @Mapping(target = "rencontreIdHtml",source = "numeroRencontre")
    RencontreDB toDatabase(Rencontre rencontre);



    @Mapping(target = "numeroRencontre",source = "rencontreIdHtml")
    Rencontre toDomain(RencontreDB rencontreDB);

    List<Rencontre> toDomain(List<RencontreDB> rencontreDBs);
}
