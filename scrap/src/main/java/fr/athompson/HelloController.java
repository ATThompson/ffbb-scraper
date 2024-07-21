package fr.athompson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    TestRepository testRepository;

    @GetMapping("/hello")
    public String sayHello() {
        return "oi!";
    }

    @GetMapping("/toto")
    public String toto() {
        var t = testRepository.findAll();
        var ent = new TestEntity();
        ent.setToto("mdr");
        testRepository.save(ent);
        return "ok";

    }

}