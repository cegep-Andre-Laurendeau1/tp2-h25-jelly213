package ca.cal.tp1.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("Emprunteur")
public class Emprunteur extends Utilisateur {

    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    List<Emprunt> emprunts;

    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    List<Amende> amendes;

    public Emprunteur(String nom, String prenom) {
        super(nom, prenom);
    }
}