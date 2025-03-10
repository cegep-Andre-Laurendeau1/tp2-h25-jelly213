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

    public DVD(String titre, int nombreExemplaires, String director, int duree, String rating) {
        super(titre, nombreExemplaires);
        this.director = director;
        this.duree = duree;
        this.rating = rating;
    }
}