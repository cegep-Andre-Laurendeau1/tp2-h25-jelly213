package ca.cal.tp2.Services;

import ca.cal.tp2.Models.CD;
import ca.cal.tp2.Persistences.CDRepositoryJPA;

public class CDService {
    private final CDRepositoryJPA cdRepository;

    public CDService(
            CDRepositoryJPA cdRepository
    ) {
        this.cdRepository = cdRepository;
    }

    public void createCD(CD cd) {
        cdRepository.save(cd);
        System.out.println("CD créé");
    }
}
