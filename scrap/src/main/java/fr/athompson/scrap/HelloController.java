package fr.athompson.scrap;

import fr.athompson.scrap.mappers.CompetitionMapper;
import fr.athompson.scrap.scrapers.classement.ClassementScraper;
import fr.athompson.scrap.scrapers.competition.CompetitionScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    TestRepository testRepository;

    @Autowired
    ClassementScraper classementScraper;

    @Autowired
    CompetitionScraper competitionMapper;

    @GetMapping("/hello")
    public String sayHello() {
        return "oi!";
    }

    @GetMapping("classement")
    public void classmeent(){
        var t = classementScraper.getData("b5e621201b7bb5e6212221c9");
    }

    @GetMapping("competition")
    public void competition(){
        var t = competitionMapper.getData("b5e621201b7b","200000002857851","200000002990537");
        System.out.println(t);
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