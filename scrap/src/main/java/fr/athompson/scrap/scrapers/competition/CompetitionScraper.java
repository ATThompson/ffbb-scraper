package fr.athompson.scrap.scrapers.competition;


import fr.athompson.scrap.entities.ComiteScrap;
import fr.athompson.scrap.entities.CompetitionScrap;
import fr.athompson.scrap.entities.JourneeScrap;
import fr.athompson.scrap.entities.classement.ClassementScrap;
import fr.athompson.scrap.enums.*;
import fr.athompson.scrap.scrapers.Scraper;
import fr.athompson.scrap.scrapers.classement.ClassementScraper;
import fr.athompson.scrap.scrapers.journee.JourneeScraper;
import fr.athompson.scrap.scrapers.page.PageScraper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
@Slf4j
public class CompetitionScraper extends Scraper<CompetitionScrap> {

    PageScraper pageScaper;

    JourneeScraper journeeScraper;

    ClassementScraper classementScraper;

    public CompetitionScraper(@Value("${ffbb.url.competition}") String uri, ChromeDriver driver, JourneeScraper journeeScraper, PageScraper pageScaper, ClassementScraper classementScraper) {
        super(uri, driver);
        this.journeeScraper = journeeScraper;
        this.pageScaper = pageScaper;
        this.classementScraper = classementScraper;
    }

    protected CompetitionScrap scrap(Document doc) throws Exception {
        var journees = new ArrayList<JourneeScrap>();
        String nomCompetition = getNomCompetition(doc);
        log.info("Competition : {} ", nomCompetition);
        String identifiantClassement = getIdentifiantClassement(doc);
        Integer nbPages = pageScaper.getData(identifiantClassement);
        for (int page = 1; page <= nbPages; page++) {
            JourneeScrap journeeScrap = journeeScraper.getData(identifiantClassement + Integer.toHexString(page));
            journeeScrap.setIdJournee(page);
            journees.add(journeeScrap);
        }
        ClassementScrap classementScrap = classementScraper.getData(identifiantClassement);
        var idTdDivisionNormlized = getIdTdDivisionNormalizedLowerCase(nomCompetition);
        return new CompetitionScrap(nomCompetition,
                classementScrap,
                journees,
                null,
                getSexe(idTdDivisionNormlized),
                getNiveau(idTdDivisionNormlized),
                getDivision(idTdDivisionNormlized),
                getCategorie(idTdDivisionNormlized),
                getNombrePoules(doc),
                getPoule(),
                getIsEspoir(idTdDivisionNormlized),
                getComite(doc),
                getIdChampionnat(),
                getIdDivision(),
                getIdPoule());
    }

    private String getIdPoule(){
        return getParamsMethod()[2];
    }
    private String getIdDivision(){
        return getParamsMethod()[1];
    }
    private  String getIdChampionnat() {
        return getParamsMethod()[0];
    }
    private String getPoule() {
        return getParamsMethod()[3];
    }

    private Boolean getIsEspoir(String idTdDivisionNormlized) {
        return idTdDivisionNormlized.contains("espoir");
    }

    private ComiteScrap getComite(Document doc) throws Exception {

        Element a = doc.getElementById("idTableCoupeChampionnat").select("a[href*=organisation]").first();

        String hrefOrganisation = a.attribute("href").getValue();
        Pattern pattern = Pattern.compile("\\.\\./organisation/(.*)\\.html");
        Matcher matcher = pattern.matcher(hrefOrganisation);
        if (!matcher.matches()) {
            throw new Exception(hrefOrganisation);
        }

        return new ComiteScrap(matcher.group(1),a.text());
    }

    private static String getIdTdDivisionNormalizedLowerCase(String idTdDivision) {
        var idTdDivisionNormlized = Normalizer.normalize(idTdDivision, Normalizer.Form.NFD);
        idTdDivisionNormlized = idTdDivisionNormlized.replaceAll("[\\p{InCombiningDiacriticalMarks}]","").toLowerCase();
        return idTdDivisionNormlized;
    }

    @Override
    public String[] getParamsMethod() {
        return super.getParamsMethod();
    }

    private String getNomCompetition(Document doc) {
        String idTdDivision;
        try{
            idTdDivision = doc.getElementById("idTdDivision").select("option[value="+getParamsMethod()[1]+"]").first().text();
        } catch (RuntimeException e) {
            idTdDivision = doc.getElementById("idTdDivision").text().trim();
        }
        return idTdDivision;
    }

    private Integer getNombrePoules(Document doc) {
        if(null != doc.getElementById("idPouleSelect"))
            return doc.getElementById("idPouleSelect").childNodeSize();
        else
            return 1;
    }

    private CategorieType getCategorie(String idTdDivisionNormlized) {
        var categorie = CategorieType.findByLibelleHtml(idTdDivisionNormlized);
        if (null != categorie){
            return categorie;
        }else{
            log.warn("Catégorie automatique à senior");
            return CategorieType.SENIOR;
        }
    }

    private DivisionType getDivision(String idTdDivisionNormlized) {
        if(idTdDivisionNormlized.contains("phase")){
            int index = idTdDivisionNormlized.indexOf("phase");
            idTdDivisionNormlized = idTdDivisionNormlized.substring(0,index);
        }
        ProLigueType proLigueType = ProLigueType.findByLibelleHtml(idTdDivisionNormlized);
        if(null == proLigueType) {
            var division = DivisionType.findBypossibiliteLibelle(idTdDivisionNormlized);
            if (null != division)
                return division;
            else {
                log.error("Division introuvable pour : " + idTdDivisionNormlized);
                return DivisionType.DIVISION_INTROUVABLE;
            }
        }else{
            if(proLigueType.in(ProLigueType.PRO_A,ProLigueType.ESPOIRS_PRO_A,ProLigueType.LF1))
                return DivisionType.DIVISION_1;
            else
                return DivisionType.DIVISION_2;
        }
    }

    private NiveauCompetitionType getNiveau(String idTdDivisionNormlized) {
        ProLigueType proLigueType = ProLigueType.findByLibelleHtml(idTdDivisionNormlized);
        if(null == proLigueType) {
            NiveauCompetitionType niveau = NiveauCompetitionType.findBypossibiliteLibelle(idTdDivisionNormlized);
            if (null == niveau)
                throw new RuntimeException("Niveau introuvable pour : " + idTdDivisionNormlized);
            else
                return niveau;
        }else {
            return NiveauCompetitionType.PRO;
        }
    }

    private SexeCompetitionType getSexe(String idTdDivisionNormlized) {
        ProLigueType proLigueType = ProLigueType.findByLibelleHtml(idTdDivisionNormlized);
        if(null == proLigueType) {
            SexeCompetitionType sexe = SexeCompetitionType.findByLibelleHtml(idTdDivisionNormlized);
            if (null == sexe)
                throw new RuntimeException("Sexe introuvable pour : " + idTdDivisionNormlized);
            else
                return sexe;
        }else{
            if(proLigueType.in(ProLigueType.LF1,ProLigueType.LF2))
                return SexeCompetitionType.FEMININ;
            else
                return SexeCompetitionType.MASCULIN;
        }
    }


    private String getIdentifiantClassement(Document doc) {
        Element td = doc.getElementById("idTableCoupeChampionnat");
        Element element = td.getElementById("idTdPoule");
        Element elt = element.select("option[selected]").first();
        if (null == elt) {
            elt = element.select("input").first();
        }
        return elt.attr("value").substring(9, 33);
    }

}
