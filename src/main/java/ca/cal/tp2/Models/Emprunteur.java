package ca.cal.tp2.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("emprunteur")
@Entity
public class Emprunteur extends Utilisateur {

    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emprunt> emprunts = new ArrayList<>();

    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Amende> amendes = new ArrayList<>();

    public Emprunteur(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }

    // Méthodes d'emprunt selon le diagramme UML
    public void emprunte(Document document) {
        // À implémenter
    }

    public void retourneDocument(Document document) {
        // À implémenter
    }

    public void payeAmende(double montant) {
        // À implémenter
    }

    public String rapportHistoriqueEmprunt() {
        // À implémenter
        return "";
    }
}