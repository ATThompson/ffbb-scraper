package fr.athompson.database.mappers;

import fr.athompson.cron.entities.OrganisationScrap;
import fr.athompson.database.entities.OrganisationDB;
import fr.athompson.domain.entities.Organisation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrganisationMapperDB {

    @Mapping(target = "organisationIdHtml", source = "idOrganisation")
    OrganisationDB toDatabase(OrganisationScrap organisation);

    @Mapping(target = "idOrganisation", source = "organisationIdHtml")
    @Mapping(target = "engagements", ignore = true)
    Organisation toEntitySansEngagements(OrganisationDB organisation);
}
