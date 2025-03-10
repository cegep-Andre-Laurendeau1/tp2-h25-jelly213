package ca.cal.tp2.Services;

import ca.cal.tp2.Models.DVD;
import ca.cal.tp2.Persistences.Interface.DVDRepository;
import ca.cal.tp2.Persistences.Interface.DocumentRepository;

public class DVDService {
    private final DVDRepository dvdRepository;
    private final DocumentRepository documentRepository;

    public DVDService(DVDRepository dvdRepository, DocumentRepository documentRepository) {
        this.dvdRepository = dvdRepository;
        this.documentRepository = documentRepository;
    }

    public void ajouterDVD(String titre,int anneePublication, int nombreExemplaires, String director, int duree, String rating) {
        // Vérifier si les informations sont valides
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre du DVD ne peut pas être vide");
        }

        if (nombreExemplaires <= 0) {
            throw new IllegalArgumentException("Le nombre d'exemplaires doit être supérieur à zéro");
        }

        // Vérifier si un document avec ce titre existe déjà
        if (documentRepository.findByTitre(titre) != null) {
            throw new IllegalStateException("Un document avec ce titre existe déjà");
        }

        // Créer le nouveau DVD
        DVD nouveauDVD = new DVD(titre,anneePublication,nombreExemplaires, director, duree, rating);

        // Sauvegarder le DVD
        dvdRepository.save(nouveauDVD);
    }

    public DVD getDVDById(Long id) {
        return dvdRepository.findById(id);
    }
}
