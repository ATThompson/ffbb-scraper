package fr.athompson.database;

import fr.athompson.database.entities.*;
import fr.athompson.database.repositories.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@RestController
public class Image {

    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    EngagementRepository engagementRepository;

    @Autowired
    EquipeRepository equipeRepository;


    @Autowired
    CompetitionRepository competitionRepository;

    @Autowired
    ClassementRepository classementRepository;

    @Autowired
    JourneeRepository journeeRepository;

    @Autowired
    RencontreRepository rencontreRepository;
    @GetMapping("/hi")
    public String sayHello2() {
        return "oi!";
    }

    @GetMapping("/getAll")
    public String getAll(){
        var organisations = organisationRepository.findAll();
        return "done";
    }

    @GetMapping("/createAll")
    public String toto2() {
        var organisation = new Organisation();
        var nom = UUID.randomUUID().toString();
        organisation.setNom(nom);
        organisationRepository.save(organisation);

        var equipe = new Equipe();
        equipe.setNom(nom);
        equipeRepository.save(equipe);

        var equipe2= new Equipe();
        equipe2.setNom(nom+"equipe2");
        equipeRepository.save(equipe2);

        var competition = new Competition();
        competition.setAnnee(2024);
        competition.setNiveau("DEPARTEMENTAL");
        competition.setCategorie("SENIORS");
        competition.setDivision("3");
        competition.setType("CHAMPIONNAT");
        competition.setSexe("MASCULIN");
        competition.setNombrePoules(1);
        competitionRepository.save(competition);

        var classement = new Classement();
        classement.setCompetition(competition);
        classement.setMatchJoues(1);
        classement.setPoints(3);
        classement.setMatchForfaits(0);
        classement.setMatchGagnes(1);
        classement.setMatchNuls(0);
        classement.setMatchPerdus(0);
        classement.setMatchPenalites(0);
        classement.setPosition(1);
        classement.setPointsEncaisses(50);
        classement.setPointsMarques(100);
        classement.setPointsDifference(-50);
        classement.setEquipe(equipe);
        classement.setCompetition(competition);

        classementRepository.save(classement);

        var engagement = new Engagement();
        engagement.setOrganisation(organisation);
        engagement.setPoule("Poule A");
        engagement.setEquipe(equipe);
        engagement.setCompetition(competition);
        engagementRepository.save(engagement);


        var journee = new Journee();
        journee.setCompetition(competition);

        journeeRepository.save(journee);

        var rencontre = new Rencontre();
        rencontre.setDate(LocalDate.now());
        rencontre.setEquipeDomicile(equipe);
        rencontre.setEquipeVisiteur(equipe2);
        rencontre.setScoreEquipeDomicile(100);
        rencontre.setScoreEquipeExterieur(50);
        rencontre.setJournee(journee);

        rencontreRepository.save(rencontre);

        return "ok";

    }



}