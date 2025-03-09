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
@DiscriminatorValue("DVD")
public class DVD extends Document {

    @Column
    private int duree;

    public DVD(String titre, String auteur, String editeur, int anneePublication, boolean disponible, int dureeMaxEmprunt, int nombreExemplaires, int duree) {
        super(titre, auteur, editeur, anneePublication, disponible, dureeMaxEmprunt,  nombreExemplaires);
        this.duree = duree;
    }

    // Méthode inutile pour DVD
    @Override
    public int getNombrePages() {
        return 0;
    }

    @Override
    public int getDureeDVD() {
        return duree;
    }

    // Méthode inutile pour DVD
    @Override
    public int getDureeCD() {
        return 0;
    }
}