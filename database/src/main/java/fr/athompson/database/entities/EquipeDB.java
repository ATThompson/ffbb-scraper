package fr.athompson.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "equipes")
public class EquipeDB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipes_id_gen")
    @SequenceGenerator(name = "equipes_id_gen", sequenceName = "equipes_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "organisation_id_html", nullable = false)
    private String organisationIdHtml;

    @OneToMany(mappedBy = "equipe")
    private Set<EngagementDB> engagements = new LinkedHashSet<>();

}