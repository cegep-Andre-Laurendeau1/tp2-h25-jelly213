package ca.cal.tp2.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private long id;

    @ManyToOne
    @JoinColumn(name = "emprunteur_id", nullable = false)
    private Emprunteur emprunteur;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateEmprunt;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateRetourPrevu;

    @Temporal(TemporalType.DATE)
    private Date dateRetourReel;

    @OneToMany(mappedBy = "emprunt", cascade = CascadeType.ALL)
    private List<EmpruntDocument> empruntDocuments;
}
