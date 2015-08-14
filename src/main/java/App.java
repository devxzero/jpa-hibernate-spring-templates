import entities.Product;
import org.hibernate.Session;

import java.util.List;

public class App {
    public static void main(String[] args) {
        demoHibernate();
    }

    public static void demoHibernate() {
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
        }
    }
}
