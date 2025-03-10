package ca.cal.tp2.Services;


import ca.cal.tp2.Models.Emprunteur;
import ca.cal.tp2.Persistences.Interface.EmprunteurRepository;

import java.util.ArrayList;

public class EmprunteurService {
    private final EmprunteurRepository emprunteurRepository;

    public EmprunteurService(EmprunteurRepository emprunteurRepository) {
        this.emprunteurRepository = emprunteurRepository;
    }

    public void creerNouvelEmprunteur(String name, String email, String phoneNumber) {
        // Vérifier si les informations sont valides
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'emprunteur ne peut pas être vide");
        }

        // Vérifier si l'emprunteur existe déjà
        Emprunteur existant = emprunteurRepository.findByName(name);
        if (existant != null) {
            throw new IllegalStateException("Un emprunteur avec ce nom existe déjà");
        }

        // Créer le nouvel emprunteur
        Emprunteur nouveauEmprunteur = new Emprunteur(name, email, phoneNumber);
        nouveauEmprunteur.setEmprunts(new ArrayList<>());
        nouveauEmprunteur.setAmendes(new ArrayList<>());

        // Sauvegarder l'emprunteur
        emprunteurRepository.save(nouveauEmprunteur);
    }

    public Emprunteur getEmprunteurByName(String name) {
        return emprunteurRepository.findByName(name);
    }

    public Emprunteur getEmprunteurById(Long id) {
        return emprunteurRepository.findById(id);
    }
}
