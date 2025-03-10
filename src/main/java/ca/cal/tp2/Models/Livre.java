package ca.cal.tp2.Models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Livre")
public class Livre extends Document {
    @Column
    private String ISBN;

    @Column
    private String auteur;

    @Column
    private String editeur;

    @Column
    private int nombrePages;

    // Livre.java - Constructeur mis à jour
    public Livre(String titre, int nombreExemplaires, int anneePublication, String ISBN, String auteur, String editeur, int nombrePages) {
        super(titre, nombreExemplaires, anneePublication);
        this.ISBN = ISBN;
        this.auteur = auteur;
        this.editeur = editeur;
        this.nombrePages = nombrePages;
    }
}