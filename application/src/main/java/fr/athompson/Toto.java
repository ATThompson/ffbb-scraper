package fr.athompson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Toto {


    @GetMapping("/bonjour")
    public String sayHello() {
        return "oi!";
    }


}