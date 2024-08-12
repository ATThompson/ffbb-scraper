package fr.athompson.domain.services.organisation;

import fr.athompson.domain.entities.Organisation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllOrganisations  {

    SPIGetAllOrganisations getAllOrganisations;

    public List<Organisation> execute() {
        return getAllOrganisations.getAllOrganisations();
    }
}
