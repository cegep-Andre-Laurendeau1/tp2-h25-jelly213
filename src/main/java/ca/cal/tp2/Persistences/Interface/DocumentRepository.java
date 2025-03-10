package ca.cal.tp2.Persistences.Interface;

import ca.cal.tp2.Models.Document;

import java.util.List;

public interface DocumentRepository {
    void save(Document document);
    Document findById(Long id);
    Document findByTitre(String titre);
    List<Document> findByTitreContaining(String keyword);
    List<Document> findByAuteur(String auteur);
    List<Document> findByAnneePublication(int annee);
    List<Document> findByArtisteContaining(String artiste);
}