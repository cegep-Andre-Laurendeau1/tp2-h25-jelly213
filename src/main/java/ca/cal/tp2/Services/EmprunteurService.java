package ca.cal.tp2.Services;

import ca.cal.tp2.Models.Amende;
import ca.cal.tp2.Models.Emprunt;
import ca.cal.tp2.Models.Emprunteur;
import ca.cal.tp2.Persistences.EmprunteurRepositoryJPA;

import java.util.List;

public class EmprunteurService {
    private final EmprunteurRepositoryJPA emprunteurRepository;

    public EmprunteurService(
        EmprunteurRepositoryJPA emprunteurRepository
    ) {
        this.emprunteurRepository = emprunteurRepository;
    }

    public void createEmprunteur(String firstName, String lastName, List<Emprunt> emprunts, List<Amende> amendes) {
        try {
            emprunteurRepository.save(new Emprunteur(lastName, firstName, emprunts, amendes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
