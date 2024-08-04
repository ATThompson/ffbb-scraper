package fr.athompson.cron;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fr.athompson.cron.spi.SPIGetOrganisation;
import fr.athompson.cron.spi.SPISaveOrganisation;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController2 {


    final SPIGetOrganisation getOrganisation;

    final SPISaveOrganisation saveOrganisation;

    @GetMapping("/tata/{idOrganisation}")
    public String qsdsq(@PathVariable String idOrganisation) throws JsonProcessingException {
        System.out.println(idOrganisation);
        var orga = getOrganisation.getOrganisation(idOrganisation);
        saveOrganisation.saveOrganisation(orga);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(orga);
        return json;
    }
    @GetMapping("/popo")
    public String qsdsq() throws JsonProcessingException {
        return "sss";
    }


}