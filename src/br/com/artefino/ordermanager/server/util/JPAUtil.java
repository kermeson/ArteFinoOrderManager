package br.com.artefino.ordermanager.server.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPAUtil {

    private static EntityManagerFactory emf;


    /**
     * Padrão singleton.
     *
     * @return EntityManagerFactory
     *         <p>
     *         uma fabrica de EntityManager
     *         </p>
     */
    private synchronized static EntityManagerFactory getEmf() {

        if (emf == null) {

            try {
                emf = Persistence.createEntityManagerFactory("ARTEFINO");
            } catch (RuntimeException ex) {
                throw ex;
            }
        }

        return emf;
    }

    private static EntityManager getEntityManager() {
        return getEmf().createEntityManager();
    }

    private static EntityTransaction getTransaction() {
        return getEntityManager().getTransaction();
    }

    public static void startTransaction() {
        getTransaction().begin();
    }

    public static void endTransaction(boolean commit) {
        if (commit) {
            getTransaction().commit();
        } else {
            getTransaction().rollback();
        }
    }

    /** Clear the persistence context, causing all managed entities to become detached. */
    public static void clear() {
        getEntityManager().clear();
    }

    /** Close an application-managed EntityManager. */
    public static void close() {
        try {
            getEntityManager().close();
        } catch (Exception e) { }
    }

    /** Create an instance of Query for executing a named query (in EJB QL or native SQL). */
    public static Query createNamedQuery(String name) {
        return getEntityManager().createNamedQuery(name);
    }

    /** Create an instance of Query for executing an EJB QL statement. */
    public static Query createQuery(String ejbqlString) {
        return getEntityManager().createQuery(ejbqlString);
    }

    /** Synchronize the persistence context to the underlying database. */
    public static void flush() {
        getEntityManager().flush();
    }

    /** Make an entity instance managed and persistent. */
    public static void persist(Object entity) {
        getEntityManager().persist(entity);
    }

    /** Refresh the state of the instance from the database, overwriting changes made to the entity, if any. */
    public static void refresh(Object entity) {
        getEntityManager().refresh(entity);
    }

    /** Remove the entity instance. */
    public static void remove(Object entity) {
        getEntityManager().remove(entity);
    }
}
