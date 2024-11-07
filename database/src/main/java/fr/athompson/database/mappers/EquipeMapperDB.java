package fr.athompson.database.mappers;


import fr.athompson.cron.entities.EquipeScrap;
import fr.athompson.database.entities.EquipeDB;
import fr.athompson.domain.entities.Equipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EquipeMapperDB {
    @Mapping(target = "organisationIdHtml", source = "idOrganisation")
    EquipeDB toDatabase(EquipeScrap equipe);
}
