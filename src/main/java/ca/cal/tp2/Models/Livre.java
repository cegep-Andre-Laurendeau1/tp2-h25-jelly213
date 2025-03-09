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
    private int nombrePages;

    public Livre(String titre, String auteur, String editeur, int anneePublication, boolean disponible, int dureeMaxEmprunt, int nombrePages, int nombreExemplaires) {
        super(titre, auteur, editeur, anneePublication, disponible, dureeMaxEmprunt, nombreExemplaires);
        this.nombrePages = nombrePages;
    }


    // Méthode inutile pour Livre
    @Override
    public int getDureeDVD() {
        return 0;
    }

    // Méthode inutile pour Livre
    @Override
    public int getDureeCD() {
        return 0;
    }
}