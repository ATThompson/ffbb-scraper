package fr.athompson.database.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "journees")
public class Journee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "journees_id_gen")
    @SequenceGenerator(name = "journees_id_gen", sequenceName = "journees_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    @OneToMany(mappedBy = "journee")
    private Set<Rencontre> rencontres = new LinkedHashSet<>();

}