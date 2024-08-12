package fr.athompson.database.mappers;

import fr.athompson.database.entities.EngagementDB;
import fr.athompson.domain.entities.engagement.Engagement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EngagementMapperDB {

    @Mapping(target = "equipe", ignore = true)
    @Mapping(target = "competition", ignore = true)
    @Mapping(target = "organisation", ignore = true)
    EngagementDB toDatabase(Engagement engagement);

    Engagement toEntity(EngagementDB engagement);
}
