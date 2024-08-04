package fr.athompson.scrap.scrapers.competition;

import fr.athompson.scrap.entities.ComiteScrap;
import fr.athompson.scrap.entities.CompetitionScrap;
import fr.athompson.scrap.entities.JourneeScrap;
import fr.athompson.scrap.entities.classement.ClassementScrap;
import fr.athompson.scrap.enums.CategorieType;
import fr.athompson.scrap.enums.DivisionType;
import fr.athompson.scrap.enums.NiveauCompetitionType;
import fr.athompson.scrap.enums.SexeCompetitionType;
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
        String identifiantClassement = getIdentifiantClassement(doc);
        Integer nbPages = pageScaper.getData(identifiantClassement);
        log.info("Nombre de journees : {} ", nbPages);
        for (int page = 1; page <= nbPages; page++) {
            journees.add(journeeScraper.getData(identifiantClassement + Integer.toHexString(page)));
        }
        ClassementScrap classementScrap = classementScraper.getData(identifiantClassement);
        var idTdDivisionNormlized = getIdTdDivisionNormalizedLowerCase(doc);

        SexeCompetitionType sexe = getSexe(idTdDivisionNormlized);
        NiveauCompetitionType niveau = getNiveau(idTdDivisionNormlized);
        DivisionType division = getDivision(idTdDivisionNormlized);
        CategorieType categorie = getCategorie(idTdDivisionNormlized);
        Integer nombrePoules= getNombrePoules(doc);
        ComiteScrap comite = getComite(doc);
        String[] paramsMethod = getParamsMethod();
        return new CompetitionScrap(classementScrap, journees, null,sexe,niveau,division,categorie,nombrePoules,comite,paramsMethod[0],paramsMethod[1],paramsMethod[2]);
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

    private static String getIdTdDivisionNormalizedLowerCase(Document doc) {
        String idTdDivision = doc.getElementById("idTdDivision").text().trim();
        var idTdDivisionNormlized = Normalizer.normalize(idTdDivision, Normalizer.Form.NFD);
        idTdDivisionNormlized = idTdDivisionNormlized.replaceAll("[\\p{InCombiningDiacriticalMarks}]","").toLowerCase();
        return idTdDivisionNormlized;
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
        var division = DivisionType.findBypossibiliteLibelle(idTdDivisionNormlized);
        if(null != division)
            return division;
        else
            throw new RuntimeException("Division introuvable pour : "+idTdDivisionNormlized);
    }

    private NiveauCompetitionType getNiveau(String idTdDivisionNormlized) {
        NiveauCompetitionType niveau = NiveauCompetitionType.findBypossibiliteLibelle(idTdDivisionNormlized);
        if(null == niveau)
            throw new RuntimeException("Niveau introuvable pour : "+idTdDivisionNormlized);
        else
            return niveau;
    }

    private SexeCompetitionType getSexe(String idTdDivisionNormlized) {
        SexeCompetitionType sexe = SexeCompetitionType.findByLibelleHtml(idTdDivisionNormlized);
        if(null == sexe)
            throw new RuntimeException("Sexe introuvable pour : "+idTdDivisionNormlized);
        else
            return sexe;
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
