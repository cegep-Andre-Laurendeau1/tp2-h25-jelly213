package ca.cal.tp2.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Amende {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fineId;

    @ManyToOne
    @JoinColumn(name = "emprunteur_id")
    private Emprunteur emprunteur;

    @Column
    private double montant;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @Column
    private boolean status;

    public Amende(Emprunteur emprunteur, double montant) {
        this.emprunteur = emprunteur;
        this.montant = montant;
        this.dateCreation = new Date();
        this.status = false; // Non payée par défaut
    }

    public double calculMontant() {
        // Cette méthode pourrait appliquer des intérêts ou des pénalités supplémentaires
        return montant;
    }

    public void updateStatus() {
        status = true; // Marque l'amende comme payée
    }
}