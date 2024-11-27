package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.Equipe;
import fr.athompson.scrap.entities.EquipeScrap;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipeMapper {

    Equipe toDomain(EquipeScrap equipeScrap);
}
