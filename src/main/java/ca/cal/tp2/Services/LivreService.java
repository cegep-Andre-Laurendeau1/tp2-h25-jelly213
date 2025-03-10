package ca.cal.tp2.Services;

import ca.cal.tp2.Models.Document;
import ca.cal.tp2.Models.Livre;
import ca.cal.tp2.Persistences.Interface.DocumentRepository;
import ca.cal.tp2.Persistences.Interface.LivreRepository;

public class LivreService {
    private final LivreRepository livreRepository;
    private final DocumentRepository documentRepository;

    public LivreService(LivreRepository livreRepository, DocumentRepository documentRepository) {
        this.livreRepository = livreRepository;
        this.documentRepository = documentRepository;
    }

    public void ajouterLivre(String titre, int nombreExemplaires, String ISBN, String auteur, String editeur, int nombrePages) {
        // Vérifier si les informations sont valides
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre du livre ne peut pas être vide");
        }

        if (nombreExemplaires <= 0) {
            throw new IllegalArgumentException("Le nombre d'exemplaires doit être supérieur à zéro");
        }

        // Vérifier si un document avec ce titre existe déjà
        Document existant = documentRepository.findByTitre(titre);
        if (existant != null) {
            throw new IllegalStateException("Un document avec ce titre existe déjà");
        }

        // Créer le nouveau livre
        Livre nouveauLivre = new Livre(titre, nombreExemplaires, ISBN, auteur, editeur, nombrePages);

        // Sauvegarder le livre
        livreRepository.save(nouveauLivre);
    }

    public Livre getLivreById(Long id) {
        return livreRepository.findById(id);
    }
}