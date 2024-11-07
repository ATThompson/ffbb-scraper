package fr.athompson.database.mappers;

import fr.athompson.cron.entities.RencontreScrap;
import fr.athompson.database.entities.RencontreDB;
import fr.athompson.domain.entities.Rencontre;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {EquipeMapperDB.class})
public interface RencontreMapperDB {

    @Mapping(target = "equipeDomicile", ignore = true)
    @Mapping(target = "equipeVisiteur", ignore = true)
    @Mapping(target = "journee", ignore = true)
    @Mapping(target = "rencontreIdHtml",source = "numeroRencontre")
    RencontreDB toDatabase(RencontreScrap rencontre);
}
