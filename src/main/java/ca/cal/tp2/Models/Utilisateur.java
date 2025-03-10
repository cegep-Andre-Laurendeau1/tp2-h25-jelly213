package ca.cal.tp2.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private long userID;

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    public Utilisateur(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Méthode pour obtenir le rôle de l'utilisateur
    public String getRole() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

    // Méthode de login (pourrait être implémentée dans une version future)
    public boolean login() {
        // Implémentation de base, à enrichir selon les besoins
        return true;
    }
}