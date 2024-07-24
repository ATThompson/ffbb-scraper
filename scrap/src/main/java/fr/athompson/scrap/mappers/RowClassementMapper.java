package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.classement.RowClassement;
import fr.athompson.scrap.entities.classement.RowClassementScrap;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RowClassementMapper {
    RowClassement toDomain(RowClassementScrap rowClassementScrap);
}
