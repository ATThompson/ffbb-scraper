package fr.athompson.scrap.mappers;

import fr.athompson.domain.entities.classement.Classement;
import fr.athompson.scrap.entities.classement.ClassementScrap;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {RowClassementMapper.class})
public interface ClassementMapper {
    Classement toDomain(ClassementScrap classementScrap);
}
