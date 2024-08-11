package fr.athompson.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "engagements")
public class EngagementDB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "engagements_id_gen")
    @SequenceGenerator(name = "engagements_id_gen", sequenceName = "engagements_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipe_id", nullable = false)
    private EquipeDB equipe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "competition_id", nullable = false)
    private CompetitionDB competition;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "organisation_id", nullable = false)
    private OrganisationDB organisation;

}