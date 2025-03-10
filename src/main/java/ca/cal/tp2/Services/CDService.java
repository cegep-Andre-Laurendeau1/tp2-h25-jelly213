package ca.cal.tp2.Services;

import ca.cal.tp2.Models.CD;
import ca.cal.tp2.Persistences.Interface.CDRepository;
import ca.cal.tp2.Persistences.Interface.DocumentRepository;

public class CDService {
    private final CDRepository cdRepository;
    private final DocumentRepository documentRepository;

    public CDService(CDRepository cdRepository, DocumentRepository documentRepository) {
        this.cdRepository = cdRepository;
        this.documentRepository = documentRepository;
    }

    public void ajouterCD(String titre, int nombreExemplaires, String artiste, int duree, String genre) {
        // Vérifier si les informations sont valides
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre du CD ne peut pas être vide");
        }

        if (nombreExemplaires <= 0) {
            throw new IllegalArgumentException("Le nombre d'exemplaires doit être supérieur à zéro");
        }

        // Vérifier si un document avec ce titre existe déjà
        if (documentRepository.findByTitre(titre) != null) {
            throw new IllegalStateException("Un document avec ce titre existe déjà");
        }

        // Créer le nouveau CD
        CD nouveauCD = new CD(titre, nombreExemplaires, artiste, duree, genre);

        // Sauvegarder le CD
        cdRepository.save(nouveauCD);
    }

    public CD getCDById(Long id) {
        return cdRepository.findById(id);
    }
}