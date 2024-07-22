package fr.athompson.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "classements")
public class Classement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classements_id_gen")
    @SequenceGenerator(name = "classements_id_gen", sequenceName = "classements_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "\"position\"", nullable = false)
    private Integer position;

    @Column(name = "points", nullable = false)
    private Integer points;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "equipe_id", nullable = false)
    private Equipe equipe;

    @Column(name = "match_joues", nullable = false)
    private Integer matchJoues;

    @Column(name = "match_gagnes", nullable = false)
    private Integer matchGagnes;

    @Column(name = "match_perdus", nullable = false)
    private Integer matchPerdus;

    @Column(name = "match_nuls", nullable = false)
    private Integer matchNuls;

    @Column(name = "match_penalites", nullable = false)
    private Integer matchPenalites;

    @Column(name = "match_forfaits", nullable = false)
    private Integer matchForfaits;

    @Column(name = "points_marques", nullable = false)
    private Integer pointsMarques;

    @Column(name = "points_encaisses", nullable = false)
    private Integer pointsEncaisses;

    @Column(name = "points_difference", nullable = false)
    private Integer pointsDifference;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

}