package ca.cal.tp1.Models;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
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

    @Column(nullable = true)
    private int nombrePages;

    public Livre(String titre, String auteur, String editeur, int anneePublication, boolean disponible, int dureeMaxEmprunt, int nombrePages) {
        super(titre, auteur, editeur, anneePublication, disponible, dureeMaxEmprunt);
        this.nombrePages = nombrePages;
    }
}