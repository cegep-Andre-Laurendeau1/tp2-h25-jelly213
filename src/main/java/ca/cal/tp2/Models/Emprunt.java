package ca.cal.tp2.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long borrowId;

    @ManyToOne
    @JoinColumn(name = "emprunteur_id", nullable = false)
    private Emprunteur emprunteur;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateEmprunt;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "emprunt", cascade = CascadeType.ALL)
    private List<EmpruntDetail> empruntDetails = new ArrayList<>();

    public Emprunt(Emprunteur emprunteur, Date dateEmprunt) {
        this.emprunteur = emprunteur;
        this.dateEmprunt = dateEmprunt;
        this.status = "En cours";
    }

    public List<EmpruntDetail> getItems() {
        return empruntDetails;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Emprunt #" + borrowId +
                "\nDate d'emprunt: " + dateFormat.format(dateEmprunt) +
                "\nStatut: " + status;
    }
}