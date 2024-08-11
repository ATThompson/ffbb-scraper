package fr.athompson.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "journees")
public class JourneeDB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "journees_id_gen")
    @SequenceGenerator(name = "journees_id_gen", sequenceName = "journees_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "competition_id", nullable = false)
    private CompetitionDB competition;

    @Column(name = "journee_id_html", nullable = false)
    private Integer journeeIdHtml;

    @OneToMany(mappedBy = "journee")
    private Set<RencontreDB> rencontres = new LinkedHashSet<>();

}