package ca.cal.tp2.Persistences;

import ca.cal.tp2.Models.Emprunteur;
import ca.cal.tp2.Persistences.Interface.EmprunteurRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;

public class EmprunteurRepositoryJPA implements EmprunteurRepository {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaTownPU");

    @Override
    public void save(Emprunteur emprunteur) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(emprunteur);
            em.getTransaction().commit();
        } catch (RollbackException e){
            throw new RuntimeException(e);
        }
    }
}
