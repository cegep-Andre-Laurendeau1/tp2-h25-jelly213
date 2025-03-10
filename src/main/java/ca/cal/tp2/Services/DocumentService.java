package ca.cal.tp2.Services;

import ca.cal.tp2.Models.Document;
import ca.cal.tp2.Persistences.Interface.DocumentRepository;

public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    public Document getDocumentByTitre(String titre) {
        return documentRepository.findByTitre(titre);
    }
}