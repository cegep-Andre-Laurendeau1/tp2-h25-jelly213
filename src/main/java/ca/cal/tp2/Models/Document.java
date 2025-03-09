package ca.cal.tp2.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_document", discriminatorType = DiscriminatorType.STRING)
public abstract class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String auteur;

    @Column(nullable = false)
    private String editeur;

    @Column(nullable = false)
    private int anneePublication;

    @Column(nullable = false)
    private boolean disponible;

    @Column(nullable = false)
    private int dureeMaxEmprunt;

    @Column(nullable = false)
    private int nombreExemplaires;



    public Document(String titre, String auteur, String editeur, int anneePublication, boolean disponible, int dureeMaxEmprunt, int nombreExemplaires) {
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.anneePublication = anneePublication;
        this.disponible = disponible;
        this.dureeMaxEmprunt = dureeMaxEmprunt;
        this.nombreExemplaires = nombreExemplaires;
    }
}
