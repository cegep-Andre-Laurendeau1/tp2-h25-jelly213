package ca.cal.tp2;

import ca.cal.tp2.Models.Livre;
import ca.cal.tp2.Persistences.EmprunteurRepositoryJPA;
import ca.cal.tp2.Persistences.LivreRepositoryJPA;
import ca.cal.tp2.Persistences.UtilisateurRepositoryJPA;
import ca.cal.tp2.Services.EmprunteurService;
import ca.cal.tp2.Services.LivreService;
import ca.cal.tp2.Services.UtilisateurService;
import ca.cal.tp2.Utils.TcpServer;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException {

        TcpServer.startTcpServer();

        // Création d'un emprunteur avec liste vide
        EmprunteurService emprunteurService = new EmprunteurService(new EmprunteurRepositoryJPA());

        emprunteurService.createEmprunteur("John", "Doe", List.of(), List.of());

        // Recherche d'un emprunteur
        UtilisateurService utilisateurService = new UtilisateurService(new UtilisateurRepositoryJPA());

       System.out.println(utilisateurService.getUtilisateurEmprunteurByName("John", "Doe"));

       // Ajout d'un livre
        LivreService livreService = new LivreService(new LivreRepositoryJPA());

        livreService.createLivre(new Livre("L'Étranger", "Albert Camus", "Gallimard", 1942, true, 30, 123, 30));
        livreService.createLivre(new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", "Gallimard", 1943, true, 10, 1234,0));
        livreService.createLivre(new Livre("Le Seigneur des Anneaux", "J.R.R. Tolkien", "Hachette", 1954, true, 5, 12345, 0));
        livreService.createLivre(new Livre("Harry Potter", "J.K. Rowling", "Gallimard", 1997, true, 20, 123456, 0));



        Thread.currentThread().join();
    }
}
