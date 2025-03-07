package ca.cal.tp1.Persistance.Emprunteur;

import ca.cal.tp1.Models.Emprunteur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmprunteurRepositoryJPA implements EmprunteurRepository {
    private  final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("javatown.pu");

    @Override
    public void save(Emprunteur emprunteur) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(emprunteur);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
