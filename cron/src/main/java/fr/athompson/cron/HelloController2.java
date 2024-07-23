package fr.athompson.cron;

import fr.athompson.cron.spi.SPIGetOrganisation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController2 {


    final SPIGetOrganisation getOrganisation;


    @GetMapping("/tata")
    public String qsdsq() {

        getOrganisation.getOrganisation("1");
        return "oqsdqi!";
    }


}