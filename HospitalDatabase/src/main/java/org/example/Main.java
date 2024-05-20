package org.example;

import tools.EntityManagerCreator;
import tools.PatientsManager;

import javax.persistence.EntityManager;

public class Main  {
    public static void main(String[] args) {
        EntityManager manager = EntityManagerCreator.getEntityManager();

        try {
            manager.getTransaction().begin();
            PatientsManager.managePatients(manager);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        } finally {
            manager.close();
        }
    }

}
