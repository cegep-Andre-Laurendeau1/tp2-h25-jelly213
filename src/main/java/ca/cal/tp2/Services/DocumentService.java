package ca.cal.tp2.Services;

import ca.cal.tp2.Models.CD;
import ca.cal.tp2.Models.DVD;
import ca.cal.tp2.Models.Document;
import ca.cal.tp2.Models.Livre;
import ca.cal.tp2.Persistences.Interface.DocumentRepository;

import java.util.List;

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

    // Méthodes de recherche
    public List<Document> rechercherParMotCle(String motCle) {
        if (motCle == null || motCle.trim().isEmpty()) {
            throw new IllegalArgumentException("Le mot-clé de recherche ne peut pas être vide");
        }
        return documentRepository.findByTitreContaining(motCle);
    }

    public List<Document> rechercherParAuteur(String auteur) {
        if (auteur == null || auteur.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'auteur ne peut pas être vide");
        }
        return documentRepository.findByAuteur(auteur);
    }

    public List<Document> rechercherParAnnee(int annee) {
        if (annee <= 0) {
            throw new IllegalArgumentException("L'année doit être positive");
        }
        return documentRepository.findByAnneePublication(annee);
    }

    public List<Document> rechercherParArtiste(String artiste) {
        if (artiste == null || artiste.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'artiste ne peut pas être vide");
        }
        return documentRepository.findByArtisteContaining(artiste);
    }

    // Méthode pour formater le résultat de recherche
    public String formaterResultatsRecherche(List<Document> documents) {
        if (documents.isEmpty()) {
            return "Aucun document trouvé correspondant à votre recherche.";
        }

        StringBuilder resultat = new StringBuilder();
        resultat.append("Résultats de la recherche (").append(documents.size()).append(" document(s) trouvé(s)) :\n");
        resultat.append("------------------------------------------------------\n");

        for (Document doc : documents) {
            resultat.append("ID: ").append(doc.getDocumentId())
                    .append(" | Type: ").append(doc.getType())
                    .append(" | Titre: ").append(doc.getTitre())
                    .append(" | Année: ").append(doc.getAnneePublication())
                    .append(" | Exemplaires disponibles: ").append(doc.getNombreExemplaires()).append("\n");

            if (doc instanceof Livre livre) {
                resultat.append("    Auteur: ").append(livre.getAuteur())
                        .append(" | Éditeur: ").append(livre.getEditeur())
                        .append(" | Pages: ").append(livre.getNombrePages()).append("\n");
            } else if (doc instanceof CD cd) {
                resultat.append("    Artiste: ").append(cd.getArtiste())
                        .append(" | Durée: ").append(cd.getDuree()).append(" min")
                        .append(" | Genre: ").append(cd.getGenre()).append("\n");
            } else if (doc instanceof DVD dvd) {
                resultat.append("    Réalisateur: ").append(dvd.getDirector())
                        .append(" | Durée: ").append(dvd.getDuree()).append(" min")
                        .append(" | Classification: ").append(dvd.getRating()).append("\n");
            }

            resultat.append("------------------------------------------------------\n");
        }

        return resultat.toString();
    }
}