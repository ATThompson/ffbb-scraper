package fr.athompson.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comites")
public class ComiteDB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comites_id_gen")
    @SequenceGenerator(name = "comites_id_gen", sequenceName = "comites_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "comite_id_html", nullable = false)
    private String comiteIdHtml;

}