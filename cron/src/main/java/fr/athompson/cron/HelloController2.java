package fr.athompson.cron;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.athompson.cron.spi.SPIGetOrganisation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController2 {


    final SPIGetOrganisation getOrganisation;


    @GetMapping("/tata")
    public String qsdsq() throws JsonProcessingException {

        var orga = getOrganisation.getOrganisation("1a961afb98b");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(orga);
        return json;
    }


}