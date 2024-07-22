package fr.athompson.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "rencontres")
public class Rencontre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rencontres_id_gen")
    @SequenceGenerator(name = "rencontres_id_gen", sequenceName = "rencontres_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "equipe_domicile_id", nullable = false)
    private Equipe equipeDomicile;

    @Column(name = "score_equipe_domicile", nullable = false)
    private Integer scoreEquipeDomicile;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "equipe_visiteur_id", nullable = false)
    private Equipe equipeVisiteur;

    @Column(name = "score_equipe_exterieur", nullable = false)
    private Integer scoreEquipeExterieur;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "journee_id", nullable = false)
    private Journee journee;

}