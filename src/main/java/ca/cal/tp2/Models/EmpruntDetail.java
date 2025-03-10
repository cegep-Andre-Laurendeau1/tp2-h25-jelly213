package ca.cal.tp2.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmpruntDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lineItemId;

    @ManyToOne
    @JoinColumn(name = "emprunt_id")
    private Emprunt emprunt;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateRetourPrevue;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dateRetourActuelle;

    @Column
    private String status;

    public EmpruntDetail(Emprunt emprunt, Document document, Date dateRetourPrevue) {
        this.emprunt = emprunt;
        this.document = document;
        this.dateRetourPrevue = dateRetourPrevue;
        this.status = "Emprunté";
    }

    public boolean isEnRetard() {
        if (dateRetourActuelle == null && new Date().after(dateRetourPrevue)) {
            return true;
        }
        return false;
    }

    public double calculAmende() {
        if (isEnRetard()) {
            // Calculer le nombre de jours de retard
            long diff = new Date().getTime() - dateRetourPrevue.getTime();
            long joursDiff = diff / (24 * 60 * 60 * 1000);

            // 0.25$ par jour de retard
            return joursDiff * 0.25;
        }
        return 0.0;
    }

    public void updateStatus() {
        if (dateRetourActuelle != null) {
            status = "Retourné";
        } else if (isEnRetard()) {
            status = "En retard";
        } else {
            status = "Emprunté";
        }
    }
}