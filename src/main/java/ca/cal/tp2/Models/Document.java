package ca.cal.tp2.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_document", discriminatorType = DiscriminatorType.STRING)
public abstract class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long documentId;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private int nombreExemplaires;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<EmpruntDetail> empruntDetails = new ArrayList<>();

    public Document(String titre, int nombreExemplaires) {
        this.titre = titre;
        this.nombreExemplaires = nombreExemplaires;
    }

    public String getType() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

    public boolean verifierDisponibilite() {
        return nombreExemplaires > 0;
    }
}