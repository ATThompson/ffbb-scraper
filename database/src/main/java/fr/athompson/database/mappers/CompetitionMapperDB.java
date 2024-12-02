package fr.athompson.database.mappers;

import fr.athompson.database.entities.CompetitionDB;
import fr.athompson.domain.entities.Competition;
import fr.athompson.domain.enums.DivisionType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {ComiteMapperDB.class})
public interface CompetitionMapperDB {

    @Named("enumDivisionToDB")
    static Integer enumDivisionToDB(DivisionType divisionType) {
        return divisionType == DivisionType.DIVISION_INTROUVABLE ? 99 : divisionType.ordinal();
    }

    @Named("divisionDBToEnum")
    static DivisionType divisionDBToEnum(Integer division) {
        return DivisionType.values().length < division ? DivisionType.DIVISION_INTROUVABLE : DivisionType.values()[division];
    }

    @Mapping(target = "division", source = "division", qualifiedByName = "enumDivisionToDB")
    @Mapping(target = "championnatIdHtml", source = "idChampionnat")
    @Mapping(target = "divisionIdHtml", source = "idDivision")
    @Mapping(target = "pouleIdHtml", source = "idPoule")
    CompetitionDB toDatabase(Competition competition);

    @Mapping(target = "idChampionnat", source = "championnatIdHtml")
    @Mapping(target = "idDivision", source = "divisionIdHtml")
    @Mapping(target = "idPoule", source = "pouleIdHtml")
    @Mapping(target = "division", source = "division", qualifiedByName = "divisionDBToEnum")
    @Mapping(target = "journees", ignore = true)
    Competition toDomainUniquementSimpleChamp(CompetitionDB competitionDB);

    default List<Competition> toDomainUniquementSimpleChamp(List<CompetitionDB> competitionDB) {
        return competitionDB.stream().map(this::toDomainUniquementSimpleChamp).toList();
    }
}
