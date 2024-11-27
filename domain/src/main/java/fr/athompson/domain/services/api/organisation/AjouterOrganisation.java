package fr.athompson.domain.services.api.organisation;

import fr.athompson.domain.entities.Organisation;
import fr.athompson.domain.services.spi.organisation.SPISauvegarderOrganisation;
import fr.athompson.domain.services.spi.organisation.SPIScrapOrganisation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AjouterOrganisation {

    SPIScrapOrganisation scrapOrganisation;
    SPISauvegarderOrganisation sauvegarderOrganisation;
    public Organisation execute(String idOrganisation) {
        Organisation organisation = scrapOrganisation.scrap(idOrganisation);
        sauvegarderOrganisation.sauvegarder(organisation);
        return organisation;
    }

}
