package ca.cal.tp2.Services;

import ca.cal.tp2.Models.DVD;
import ca.cal.tp2.Persistences.DVDRepositoryJPA;

public class DVDService {
    private final DVDRepositoryJPA dvdRepository;

    public DVDService(
            DVDRepositoryJPA dvdRepository
    ) {
        this.dvdRepository = dvdRepository;
    }

    public void createDVD(DVD dvd) {
        dvdRepository.save(dvd);
        System.out.println("DVD créé");
    }
}
