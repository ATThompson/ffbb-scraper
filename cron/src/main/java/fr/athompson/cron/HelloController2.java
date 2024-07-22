package fr.athompson.cron;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController2 {

    @GetMapping("/tata")
    public String qsdsq() {
        return "oqsdqi!";
    }


}