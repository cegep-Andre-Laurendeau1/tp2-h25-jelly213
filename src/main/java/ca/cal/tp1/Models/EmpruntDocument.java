package ca.cal.tp1.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmpruntDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "emprunt_id", nullable = false)
    private Emprunt emprunt;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    public EmpruntDocument(Emprunt emprunt, Document document) {
        this.emprunt = emprunt;
        this.document = document;
    }
}

