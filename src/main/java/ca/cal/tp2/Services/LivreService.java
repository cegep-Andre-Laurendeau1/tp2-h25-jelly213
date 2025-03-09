package ca.cal.tp2.Services;

import ca.cal.tp2.Models.Livre;
import ca.cal.tp2.Persistences.LivreRepositoryJPA;

public class LivreService {
    private final LivreRepositoryJPA livreRepositoryJPA;

    public LivreService(
            LivreRepositoryJPA livreRepositoryJPA
    ) {
        this.livreRepositoryJPA = livreRepositoryJPA;
    }

    public void createLivre(Livre livre) {
        livreRepositoryJPA.save(livre);
        System.out.println("Livre créé");
    }
}
