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
@DiscriminatorValue("CD")
public class CD extends Document {
    @Column
    private String artiste;

    @Column
    private int duree;

    @Column
    private String genre;

    public CD(String titre, int nombreExemplaires, String artiste, int duree, String genre) {
        super(titre, nombreExemplaires);
        this.artiste = artiste;
        this.duree = duree;
        this.genre = genre;
    }
}