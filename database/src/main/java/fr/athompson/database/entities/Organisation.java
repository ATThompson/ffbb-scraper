package fr.athompson.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "organisations")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organisations_id_gen")
    @SequenceGenerator(name = "organisations_id_gen", sequenceName = "organisations_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @OneToMany(mappedBy = "organisation")
    private Set<Engagement> engagements = new LinkedHashSet<>();

}