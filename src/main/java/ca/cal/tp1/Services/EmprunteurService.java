package ca.cal.tp1.Services;

import ca.cal.tp1.Models.Emprunteur;
import ca.cal.tp1.Persistance.Emprunteur.EmprunteurRepository;
import ca.cal.tp1.Persistance.Emprunteur.EmprunteurRepositoryJPA;

public class EmprunteurService {

    private final EmprunteurRepositoryJPA emprunteurRepsitoryJPA;

    public EmprunteurService(EmprunteurRepositoryJPA emprunteurRepsitoryJPA) {
        this.emprunteurRepsitoryJPA = emprunteurRepsitoryJPA;
    }

    public void saveEmprunteur(Emprunteur emprunteur){
        emprunteurRepsitoryJPA.save(emprunteur);
    }
}