package ca.cal.tp1.Models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("DVD")
public class DVD extends Document {

    @Column(nullable = true)
    private int duree;

    public DVD(String titre, String auteur, String editeur, int anneePublication, boolean disponible, int dureeMaxEmprunt, int duree) {
        super(titre, auteur, editeur, anneePublication, disponible, dureeMaxEmprunt);
        this.duree = duree;
    }
}