import entities.Product;
import jpa.JpaUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class JpaTests {
    @BeforeClass
    public static void boot() {
        JpaUtil.boot();
    }

    @AfterClass
    public static void shutdown() {
        JpaUtil.shutdown();
    }

    @Test
    public void testSaveAndQuery() {
        {
            EntityManager entityManager = JpaUtil.getCurrentEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(new Product("Product A"));
            entityManager.persist((new Product("Product B")));

            entityManager.getTransaction().commit();
            entityManager.close();
        }

        {
            EntityManager entityManager = JpaUtil.getCurrentEntityManager();
            entityManager.getTransaction().begin();

            List<Product> products = entityManager.createQuery("select p from Product p").getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();

            assertTrue(products.size() >= 2);
        }
    }
}
