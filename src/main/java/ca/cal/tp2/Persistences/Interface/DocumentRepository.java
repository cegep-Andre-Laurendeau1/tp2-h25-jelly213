package ca.cal.tp2.Persistences.Interface;

import ca.cal.tp2.Models.Document;

public interface DocumentRepository {
    void save(Document document);
    Document findById(Long id);
    Document findByTitre(String titre);
}