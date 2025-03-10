package ca.cal.tp2.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("prepose")
public class Prepose extends Utilisateur {

    public Prepose(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }

    public void entreNouveauDocument(Document document) {
        // Logique pour ajouter un nouveau document
        System.out.println("Nouveau document ajouté: " + document.getTitre());
    }

    public void collecteAmende(Emprunteur emprunteur, double montant) {
        // Logique pour collecter une amende
        System.out.println("Amende de " + montant + " $ collectée auprès de " + emprunteur.getName());
    }

    public void rapportAmendes() {
        // Génère un rapport sur les amendes
        System.out.println("Rapport des amendes généré");
    }

    public void rapportEmprunts() {
        // Génère un rapport sur les emprunts
        System.out.println("Rapport des emprunts généré");
    }
}