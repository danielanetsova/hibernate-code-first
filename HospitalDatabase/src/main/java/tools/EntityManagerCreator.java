package tools;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerCreator {

    public static EntityManager getEntityManager() {
        return Persistence
                .createEntityManagerFactory("hospital_db")
                .createEntityManager();
    }
}
