package fr.athompson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @GetMapping("/api")
    public String api() {
        return "Hello World";
    }
}
