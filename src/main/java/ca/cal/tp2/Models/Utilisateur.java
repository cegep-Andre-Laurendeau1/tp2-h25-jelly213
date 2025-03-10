package ca.cal.tp2.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private String nom;

    @Column()
    private String prenom;

    public Utilisateur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    //get Role from DiscriminatorColumn
    public String getRole() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

    //get List of Emprunts from child table Emprunteur
    public abstract List<Emprunt> getEmprunts();

    //get List of Amendes from child table Emprunteur
    public abstract List<Amende> getAmendes();


}
