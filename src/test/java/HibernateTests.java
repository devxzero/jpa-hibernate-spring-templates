import entities.Product;
import org.hibernate.Session;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HibernateTests {
    @BeforeClass
    public static void boot() {
        HibernateUtil.boot();
    }

    @AfterClass
    public static void shutdown() {
        HibernateUtil.shutdown();
    }

    @Test
    public void testSaveAndQuery() {
        {
            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            session.save(new Product("Product A"));
            session.save(new Product("Product B"));

            session.getTransaction().commit();
        }

        {
            Session session = HibernateUtil.getCurrentSession();
            session.beginTransaction();

            List<Product> products = session.createQuery("from Product").list();
            session.getTransaction().commit();

            assertTrue(products.size() >= 2);
        }
    }
}
