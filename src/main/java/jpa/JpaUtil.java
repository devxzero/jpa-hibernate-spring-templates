package jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static final Logger logger = LoggerFactory.getLogger(JpaUtil.class);

    private static EntityManagerFactory entityManagerFactory;
    private static ThreadLocal<EntityManager> entityManagerThreadLocal = new ThreadLocal<>();

    static {
        logger.info("Creating entityManagerFactory");
        // This one automatically uses META-INF/persistence.xml
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-hibernate");
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    /**
     * EntityManager doesn't implement a *current* thread scoped EntityManager like Hibernates
     * SessionFactory.getCurrentSession(). This approach manually accomplishes the same by using ThreadLocal.
     *
     */
    public static EntityManager getCurrentEntityManager() {
        EntityManager entityManager = entityManagerThreadLocal.get();
        if (entityManager == null || ! entityManager.isOpen()) {
            entityManager = entityManagerFactory.createEntityManager();
            entityManagerThreadLocal.set(entityManager);
        }

        return entityManager;
    }

    /**
     * Empty method. A call to this method causes the static initializer to be run, and thus boots the EntityManagerFactory.
     */
    public static void boot() {}

    /**
     * Shuts down the EntityManagerFactory, otherwise the Main thread may not be closed.
     */
    public static void shutdown() {
        entityManagerFactory.close();
    }
}
