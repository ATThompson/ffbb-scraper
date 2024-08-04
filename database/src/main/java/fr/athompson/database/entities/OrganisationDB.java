package fr.athompson.database.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "organisations")
public class OrganisationDB {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organisations_id_gen")
    @SequenceGenerator(name = "organisations_id_gen", sequenceName = "organisations_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "organisation_id_html", nullable = false)
    private String organisationIdHtml;

}