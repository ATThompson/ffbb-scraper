package fr.athompson.domain.services.api.organisation;

import fr.athompson.domain.entities.Organisation;
import fr.athompson.domain.services.spi.organisation.SPIChercherToutesOrganisations;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecupererToutesOrganisations {

    SPIChercherToutesOrganisations getAllOrganisations;

    public List<Organisation> execute() {
        return getAllOrganisations.chercher();
    }
}
