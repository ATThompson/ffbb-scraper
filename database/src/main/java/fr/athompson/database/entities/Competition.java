package fr.athompson.database.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competitions_id_gen")
    @SequenceGenerator(name = "competitions_id_gen", sequenceName = "competitions_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "niveau", nullable = false)
    private String niveau;

    @Column(name = "division")
    private String division;

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

    @OneToMany(mappedBy = "competition")
    private Set<Classement> classements = new LinkedHashSet<>();

    @OneToMany(mappedBy = "competition")
    private Set<Journee> journees = new LinkedHashSet<>();

}