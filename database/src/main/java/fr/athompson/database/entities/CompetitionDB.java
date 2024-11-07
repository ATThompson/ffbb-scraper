package fr.athompson.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "competitions")
public class CompetitionDB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competitions_id_gen")
    @SequenceGenerator(name = "competitions_id_gen", sequenceName = "competitions_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "niveau", nullable = false)
    private String niveau;

    @Column(name = "division", nullable = false)
    private Integer division;

    @Column(name = "categorie", nullable = false)
    private String categorie;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "sexe", nullable = false)
    private String sexe;

    @Column(name = "nombre_poules", nullable = false)
    private Integer nombrePoules;

    @Column(name = "annee", nullable = false)
    private Integer annee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comite_id", nullable = false)
    private ComiteDB comite;

    @Column(name = "organisation_id_html", nullable = false)
    private String organisationIdHtml;

    @Column(name = "division_id_html", nullable = false)
    private String divisionIdHtml;

    @Column(name = "poule_id_html", nullable = false)
    private String pouleIdHtml;

    @OneToMany(mappedBy = "competition")
    private Set<ClassementDB> classements = new LinkedHashSet<>();

    @Column(name = "is_espoir")
    private Boolean isEspoir;

    @OneToMany(mappedBy = "competition")
    private Set<JourneeDB> journees = new LinkedHashSet<>();

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "poule")
    private String poule;

    @OneToMany(mappedBy = "competition")
    private Set<EngagementDB> engagements = new LinkedHashSet<>();

}