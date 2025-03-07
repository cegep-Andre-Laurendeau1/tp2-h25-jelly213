package ca.cal.tp1.Models;


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
@Entity
@DiscriminatorValue("CD")
public class CD extends Document {

    @Column(nullable = true)
    private int duree;

    public CD(String titre, String auteur, String editeur, int anneePublication, boolean disponible, int dureeMaxEmprunt, int duree) {
        super(titre, auteur, editeur, anneePublication, disponible, dureeMaxEmprunt);
        this.duree = duree;
    }
}
