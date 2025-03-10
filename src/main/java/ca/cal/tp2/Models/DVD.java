package ca.cal.tp2.Models;

import jakarta.persistence.Column;
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
@DiscriminatorValue("DVD")
public class DVD extends Document {
    @Column
    private String director;

    @Column
    private int duree;

    @Column
    private String rating;

    // DVD.java - Constructeur mis à jour
    public DVD(String titre, int nombreExemplaires, int anneePublication, String director, int duree, String rating) {
        super(titre, nombreExemplaires, anneePublication);
        this.director = director;
        this.duree = duree;
        this.rating = rating;
    }
}