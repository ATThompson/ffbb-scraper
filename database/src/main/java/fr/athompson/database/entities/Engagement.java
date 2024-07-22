package fr.athompson.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "engagements")
public class Engagement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "engagements_id_gen")
    @SequenceGenerator(name = "engagements_id_gen", sequenceName = "engagements_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "poule")
    private String poule;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "equipe_id", nullable = false)
    private Equipe equipe;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organisation_id", nullable = false)
    private Organisation organisation;

}