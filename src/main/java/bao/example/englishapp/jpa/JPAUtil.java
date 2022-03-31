/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Bao
 */
public class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
    private static EntityManagerFactory factory;
    private static EntityManager manager;
    
    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }

    public static void shutdown() {
        if (factory != null)
            factory.close();
    }
  
    public static void createManager()
    {
        new Thread(() -> {
            manager = getEntityManagerFactory().createEntityManager();
        }).start();
    }

    public static EntityManager getManager() {
        return manager;
    }

    public static void setManager(EntityManager manager) {
        JPAUtil.manager = manager;
    }
}