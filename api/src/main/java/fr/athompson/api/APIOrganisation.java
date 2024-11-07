package fr.athompson.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.athompson.domain.services.organisation.GetAllOrganisations;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organisations")
@AllArgsConstructor
public class APIOrganisation {

    GetAllOrganisations getAllOrganisations;

    @GetMapping
    public String getOrganisation() throws JsonProcessingException {
        var test = getAllOrganisations.execute();
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.writeValueAsString(test);
        }
}
