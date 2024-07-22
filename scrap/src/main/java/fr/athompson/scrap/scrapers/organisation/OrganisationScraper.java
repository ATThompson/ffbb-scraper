package fr.athompson.scrap.scrapers.organisation;

import fr.athompson.scrap.entities.Organisation;
import fr.athompson.scrap.entities.engagement.Engagement;
import fr.athompson.scrap.scrapers.Scraper;
import fr.athompson.scrap.scrapers.api.APIEngagementScraper;
import fr.athompson.scrap.scrapers.api.APIOrganisationScraper;
import fr.athompson.scrap.utils.ScrapUtils;
import org.jsoup.nodes.Document;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganisationScraper extends Scraper<Organisation> implements APIOrganisationScraper {

    APIEngagementScraper engagementScraper;

    public OrganisationScraper(@Value("${ffbb.url.organisation}") String uri, ChromeDriver driver, APIEngagementScraper engagementScraper) {
        super(uri, driver);
        this.engagementScraper = engagementScraper;
    }

    protected Organisation scrap(Document doc) {
        //TODO: récuperer le nom de l'organisation
        String idOrganisation = getIdOrganisation(doc);
        var ligneInfosOrganisation = ScrapUtils.getFirstElementText(doc, "td.titre-bloc");
        var infosClub = ligneInfosOrganisation.split("-");
        String nomOrganisation = getNomOrganisation(infosClub);
        List<Engagement> engagements = engagementScraper.getData(idOrganisation);
        return new Organisation(nomOrganisation, engagements);
    }

    private String getIdOrganisation(Document doc) {
        return doc.getElementById("idIframeEngagement").
                attr("src").
                replaceAll("engagements/|.html", "");
    }

    private String getNomOrganisation(String[] infosOrganisation) {
        String nomOrganisation;
        int nbElementsAttendus = 3, indexNomOrganisation = 0, enleverReferenceEtTypeClub = 2;
        int nbElementsNomOrganisation = infosOrganisation.length - enleverReferenceEtTypeClub;

        if (nbElementsAttendus == infosOrganisation.length) {
            nomOrganisation = infosOrganisation[indexNomOrganisation];
        } else {
            nomOrganisation = Arrays.stream(infosOrganisation)
                    .limit(nbElementsNomOrganisation)
                    .map(String::trim)
                    .collect(Collectors.joining("-"));
        }
        return nomOrganisation;
    }
}