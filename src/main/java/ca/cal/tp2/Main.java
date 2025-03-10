package ca.cal.tp2;

import ca.cal.tp2.Models.Document;
import ca.cal.tp2.Models.Emprunteur;
import ca.cal.tp2.Persistences.*;
import ca.cal.tp2.Services.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialisation des repositories
        DocumentRepositoryJPA documentRepository = new DocumentRepositoryJPA();
        LivreRepositoryJPA livreRepository = new LivreRepositoryJPA();
        CDRepositoryJPA cdRepository = new CDRepositoryJPA();
        DVDRepositoryJPA dvdRepository = new DVDRepositoryJPA();
        EmprunteurRepositoryJPA emprunteurRepository = new EmprunteurRepositoryJPA();

        // Initialisation des services
        DocumentService documentService = new DocumentService(documentRepository);
        LivreService livreService = new LivreService(livreRepository, documentRepository);
        CDService cdService = new CDService(cdRepository, documentRepository);
        DVDService dvdService = new DVDService(dvdRepository, documentRepository);
        EmprunteurService emprunteurService = new EmprunteurService(emprunteurRepository);

        // DÉMONSTRATION DE L'ENREGISTREMENT D'UN EMPRUNTEUR
        System.out.println("===== ENREGISTREMENT D'UN NOUVEL EMPRUNTEUR =====");

        try {
            // Création d'un nouvel emprunteur
            emprunteurService.creerNouvelEmprunteur("John Doe", "john.doe@example.com", "514-123-4567");
            System.out.println("Emprunteur créé avec succès.");

            // Récupération et affichage des détails de l'emprunteur
            Emprunteur emprunteur = emprunteurService.getEmprunteurByName("John Doe");
            System.out.println("Détails de l'emprunteur récupéré:");
            System.out.println("  Nom: " + emprunteur.getName());
            System.out.println("  Email: " + emprunteur.getEmail());
            System.out.println("  Téléphone: " + emprunteur.getPhoneNumber());
            System.out.println("  Rôle: " + emprunteur.getRole());

            // Création d'un second emprunteur
            emprunteurService.creerNouvelEmprunteur("Jane Smith", "jane.smith@example.com", "514-555-9876");
            System.out.println("Deuxième emprunteur créé avec succès.");

        } catch (Exception e) {
            System.out.println("Erreur lors de l'enregistrement de l'emprunteur: " + e.getMessage());
        }

        // DÉMONSTRATION DE L'AJOUT DE DOCUMENTS
        System.out.println("\n===== AJOUT DE DOCUMENTS À LA BIBLIOTHÈQUE =====");

        try {
            // Ajout de livres avec année de publication
            System.out.println("\n--- Ajout de livres ---");

            livreService.ajouterLivre("Les Misérables", 3, 1862, "978-2253096344", "Victor Hugo", "Pocket", 1798);
            System.out.println("Livre 'Les Misérables' ajouté avec succès.");

            livreService.ajouterLivre("Les parapluies sont disparus", 2, 2010, "978-1234567890", "Auteur Imaginaire", "Édition Fictive", 256);
            System.out.println("Livre 'Les parapluies sont disparus' ajouté avec succès.");

            livreService.ajouterLivre("Crime et Châtiment", 4, 1866, "978-2070422524", "Fyodor Dostoevsky", "Gallimard", 704);
            System.out.println("Livre 'Crime et Châtiment' ajouté avec succès.");

            livreService.ajouterLivre("L'Étranger", 3, 1942, "978-2070360024", "Albert Camus", "Gallimard", 185);
            System.out.println("Livre 'L'Étranger' ajouté avec succès.");

            livreService.ajouterLivre("Le Petit Prince", 5, 1943, "978-2070612758", "Antoine de Saint-Exupéry", "Gallimard", 96);
            System.out.println("Livre 'Le Petit Prince' ajouté avec succès.");

            // Ajout de CDs avec année de publication
            System.out.println("\n--- Ajout de CDs ---");

            cdService.ajouterCD("Abbey Road", 2, 1969, "The Beatles", 47, "Rock");
            System.out.println("CD 'Abbey Road' ajouté avec succès.");

            cdService.ajouterCD("Thriller", 3, 1982, "Michael Jackson", 42, "Pop");
            System.out.println("CD 'Thriller' ajouté avec succès.");

            cdService.ajouterCD("Back in Black", 3, 1980, "AC/DC", 42, "Hard Rock");
            System.out.println("CD 'Back in Black' ajouté avec succès.");

            // Ajout de DVDs avec année de publication
            System.out.println("\n--- Ajout de DVDs ---");

            dvdService.ajouterDVD("Le Parrain", 2, 1972, "Francis Ford Coppola", 175, "R");
            System.out.println("DVD 'Le Parrain' ajouté avec succès.");

            dvdService.ajouterDVD("Inception", 2, 2010, "Christopher Nolan", 148, "PG-13");
            System.out.println("DVD 'Inception' ajouté avec succès.");

            dvdService.ajouterDVD("The Matrix", 3, 1999, "Wachowski Sisters", 136, "R");
            System.out.println("DVD 'The Matrix' ajouté avec succès.");

            dvdService.ajouterDVD("Parasite", 3, 2019, "Bong Joon-ho", 132, "R");
            System.out.println("DVD 'Parasite' ajouté avec succès.");

        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout des documents: " + e.getMessage());
        }

        // DÉMONSTRATION DE LA RECHERCHE DE DOCUMENTS
        System.out.println("\n===== RECHERCHE DE DOCUMENTS =====");

        // 1. Recherche par mot-clé dans le titre
        System.out.println("\n--- Recherche par mot-clé dans le titre ---");

        try {
            // Recherche avec le mot "parapluies"
            List<Document> resultatParapluies = documentService.rechercherParMotCle("parapluies");
            System.out.println(documentService.formaterResultatsRecherche(resultatParapluies));

            // Recherche avec le mot "disparus"
            List<Document> resultatDisparus = documentService.rechercherParMotCle("disparus");
            System.out.println(documentService.formaterResultatsRecherche(resultatDisparus));

            // Recherche avec "Les"
            List<Document> resultatLes = documentService.rechercherParMotCle("Les");
            System.out.println(documentService.formaterResultatsRecherche(resultatLes));

            // 2. Recherche par auteur
            System.out.println("\n--- Recherche par auteur ---");

            List<Document> resultatAuteur = documentService.rechercherParAuteur("Victor Hugo");
            System.out.println(documentService.formaterResultatsRecherche(resultatAuteur));

            // 3. Recherche par année
            System.out.println("\n--- Recherche par année ---");

            List<Document> resultatAnnee = documentService.rechercherParAnnee(1969);
            System.out.println(documentService.formaterResultatsRecherche(resultatAnnee));

            // 4. Recherche par artiste (pour CD et DVD)
            System.out.println("\n--- Recherche par artiste/réalisateur ---");

            List<Document> resultatArtiste = documentService.rechercherParArtiste("Beatles");
            System.out.println(documentService.formaterResultatsRecherche(resultatArtiste));

            // Recherche par réalisateur
            List<Document> resultatRealisateur = documentService.rechercherParArtiste("Nolan");
            System.out.println(documentService.formaterResultatsRecherche(resultatRealisateur));

            // 5. Recherches complémentaires pour démontrer la fonctionnalité
            System.out.println("\n--- Recherches complémentaires ---");

            // Recherche tous les livres contenant "Petit"
            List<Document> resultatPetit = documentService.rechercherParMotCle("Petit");
            System.out.println(documentService.formaterResultatsRecherche(resultatPetit));

            // Recherche par année pour trouver plusieurs documents
            List<Document> resultatAnnee2010 = documentService.rechercherParAnnee(2010);
            System.out.println(documentService.formaterResultatsRecherche(resultatAnnee2010));

        } catch (Exception e) {
            System.out.println("Erreur lors de la recherche: " + e.getMessage());
        }

        System.out.println("\n===== FIN DE LA DÉMONSTRATION =====");
    }
}
