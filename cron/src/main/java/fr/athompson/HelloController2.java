package fr.athompson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController2 {

    @GetMapping("/titi")
    public String qsdsq() {
        return "oi!";
    }


}