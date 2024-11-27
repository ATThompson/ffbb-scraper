package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.Rencontre;
import fr.athompson.scrap.entities.RencontreScrap;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = EquipeMapper.class)
public interface RencontreMapper {

    Rencontre toDomain(RencontreScrap rencontreScrap);
}
