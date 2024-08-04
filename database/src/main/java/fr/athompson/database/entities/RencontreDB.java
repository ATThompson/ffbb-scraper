package fr.athompson.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "rencontres")
public class RencontreDB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rencontres_id_gen")
    @SequenceGenerator(name = "rencontres_id_gen", sequenceName = "rencontres_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipe_domicile_id", nullable = false)
    private EquipeDB equipeDomicile;

    @Column(name = "score_domicile", nullable = false)
    private Integer scoreDomicile;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipe_visiteur_id", nullable = false)
    private EquipeDB equipeVisiteur;

    @Column(name = "score_visiteur", nullable = false)
    private Integer scoreVisiteur;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "journee_id", nullable = false)
    private JourneeDB journee;

}