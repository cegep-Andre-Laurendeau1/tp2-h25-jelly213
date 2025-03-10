package ca.cal.tp2;
import ca.cal.tp2.Models.*;
import ca.cal.tp2.Persistences.Interface.*;
import ca.cal.tp2.Services.*;
import ca.cal.tp2.Persistences.*;
import ca.cal.tp2.Services.EmprunteurService;
import ca.cal.tp2.Utils.TcpServer;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {

        TcpServer.startTcpServer();

        // Initialisation des repositories
        DocumentRepository documentRepository = new DocumentRepositoryJPA();
        LivreRepository livreRepository = new LivreRepositoryJPA();
        CDRepository cdRepository = new CDRepositoryJPA();
        DVDRepository dvdRepository = new DVDRepositoryJPA();
        EmprunteurRepository emprunteurRepository = new EmprunteurRepositoryJPA();

        // Initialisation des services
        DocumentService documentService = new DocumentService(documentRepository);
        LivreService livreService = new LivreService(livreRepository, documentRepository);
        CDService cdService = new CDService(cdRepository, documentRepository);
        DVDService dvdService = new DVDService(dvdRepository, documentRepository);
        EmprunteurService emprunteurService = new EmprunteurService(emprunteurRepository);

        // Code précédent pour l'enregistrement d'un emprunteur...

        // DÉMONSTRATION DE L'AJOUT DE DOCUMENTS
        System.out.println("\n===== AJOUT DE DOCUMENTS À LA BIBLIOTHÈQUE =====");

        // 1. Ajout de livres
        System.out.println("\n--- Ajout de livres ---");

        try {
            livreService.ajouterLivre("L'Étranger", 3, "978-2070360024", "Albert Camus", "Gallimard", 185);
            System.out.println("Livre 'L'Étranger' ajouté avec succès.");

            livreService.ajouterLivre("Le Petit Prince", 5, "978-2070612758", "Antoine de Saint-Exupéry", "Gallimard", 96);
            System.out.println("Livre 'Le Petit Prince' ajouté avec succès.");

            livreService.ajouterLivre("1984", 2, "978-2070368228", "George Orwell", "Gallimard", 438);
            System.out.println("Livre '1984' ajouté avec succès.");

            // Tentative d'ajout d'un livre avec un titre existant (doit échouer)
            livreService.ajouterLivre("L'Étranger", 1, "978-2070360024", "Albert Camus", "Autre éditeur", 190);
        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue: " + e.getMessage());
        }

        // 2. Ajout de CDs
        System.out.println("\n--- Ajout de CDs ---");

        try {
            cdService.ajouterCD("Thriller", 2, "Michael Jackson", 42, "Pop");
            System.out.println("CD 'Thriller' ajouté avec succès.");

            cdService.ajouterCD("Dark Side of the Moon", 3, "Pink Floyd", 43, "Rock");
            System.out.println("CD 'Dark Side of the Moon' ajouté avec succès.");

            // Tentative d'ajout avec nombre d'exemplaires invalide
            cdService.ajouterCD("Bad", 0, "Michael Jackson", 48, "Pop");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur attendue: " + e.getMessage());
        }

        // 3. Ajout de DVDs
        System.out.println("\n--- Ajout de DVDs ---");

        try {
            dvdService.ajouterDVD("Inception", 2, "Christopher Nolan", 148, "PG-13");
            System.out.println("DVD 'Inception' ajouté avec succès.");

            dvdService.ajouterDVD("The Matrix", 3, "Wachowski Sisters", 136, "R");
            System.out.println("DVD 'The Matrix' ajouté avec succès.");

            // Tentative d'ajout d'un DVD avec un titre vide
            dvdService.ajouterDVD("", 2, "James Cameron", 162, "PG-13");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur attendue: " + e.getMessage());
        }

        // 4. Vérification des documents ajoutés
        System.out.println("\n--- Vérification des documents ajoutés ---");

        Livre livre = (Livre) documentService.getDocumentByTitre("L'Étranger");
        if (livre != null) {
            System.out.println("Livre trouvé: " + livre.getTitre());
            System.out.println("  Auteur: " + livre.getAuteur());
            System.out.println("  Exemplaires disponibles: " + livre.getNombreExemplaires());
            System.out.println("  ISBN: " + livre.getISBN());
            System.out.println("  Nombre de pages: " + livre.getNombrePages());
        }

        CD cd = (CD) documentService.getDocumentByTitre("Thriller");
        if (cd != null) {
            System.out.println("\nCD trouvé: " + cd.getTitre());
            System.out.println("  Artiste: " + cd.getArtiste());
            System.out.println("  Exemplaires disponibles: " + cd.getNombreExemplaires());
            System.out.println("  Durée: " + cd.getDuree() + " minutes");
            System.out.println("  Genre: " + cd.getGenre());
        }

        DVD dvd = (DVD) documentService.getDocumentByTitre("Inception");
        if (dvd != null) {
            System.out.println("\nDVD trouvé: " + dvd.getTitre());
            System.out.println("  Réalisateur: " + dvd.getDirector());
            System.out.println("  Exemplaires disponibles: " + dvd.getNombreExemplaires());
            System.out.println("  Durée: " + dvd.getDuree() + " minutes");
            System.out.println("  Classification: " + dvd.getRating());
        }

        Thread.currentThread().join();
    }
}