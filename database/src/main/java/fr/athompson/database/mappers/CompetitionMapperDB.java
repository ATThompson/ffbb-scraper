package fr.athompson.database.mappers;

import fr.athompson.database.entities.CompetitionDB;
import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.enums.DivisionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface CompetitionMapperDB {

    @Mapping(target = "division", source="division", qualifiedByName = "enumDivisionToDB")
    @Mapping(target = "organisationIdHtml", source = "idOrganisation")
    @Mapping(target = "divisionIdHtml", source = "idDivision")
    @Mapping(target = "pouleIdHtml", source = "idPoule")
    CompetitionDB toDatabase(Competition competition);

    @Mapping(target = "idOrganisation", source = "organisationIdHtml")
    @Mapping(target = "idDivision", source = "divisionIdHtml")
    @Mapping(target = "idPoule", source = "pouleIdHtml")
    Competition toDomainUniquementSimpleChamp(CompetitionDB competitionDB);

    default Competition toMetaData(CompetitionDB competitionDB){
        return Competition.builder()
                .idOrganisation(competitionDB.getOrganisationIdHtml())
                .idDivision(competitionDB.getDivisionIdHtml())
                .idPoule(competitionDB.getPouleIdHtml())
                .build();
    }

    default List<Competition> toMetaData(List<CompetitionDB> competitionDB){
        return competitionDB.stream().map(this::toMetaData).toList();
    }


    @Named("enumDivisionToDB")
    public static Integer enumDivisionToDB(DivisionType divisionType){
        return divisionType.ordinal();
    }
}
