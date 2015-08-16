import entities.Product;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import spring.services.JpaProductService;
import spring.config.JpaSpringConfig;
import spring.services.HibernateProductService;
import spring.config.SpringDataJpaConfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class App {
    public static void main(String[] args) {
//        demoHibernate();
//        demoHibernateSpringXmlProgrammaticTransaction();
//        demoHibernateSpringXmlTransactionAnnotations();
//        demoHibernateSpringXmlTransactionManager();
//        demoJpaSpringJavaConfig();
//        demoJpaSpringXml();
//        demoJpaSpringJavaConfig();
//        demoSpringJavaDataJpa();
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
        HibernateUtil.shutdown();
    }

    public static void demoHibernateSpringXmlTransactionManager() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
        PlatformTransactionManager transactionManager = ctx.getBean(PlatformTransactionManager.class);

        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
        List<Product> products = session.createQuery("from Product").list();
        for (Product product : products) {
            System.out.println(product);
        }

        transactionManager.commit(transactionStatus);
    }

    public static void demoHibernateSpringXmlProgrammaticTransaction() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
        PlatformTransactionManager transactionManager = ctx.getBean(PlatformTransactionManager.class);
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                List<Product> products = sessionFactory.getCurrentSession().createQuery("from Product").list();
                for (Product product : products) {
                    System.out.println(product);
                }
            }
        });
    }

    public static void demoHibernateSpringXmlTransactionAnnotations() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-tx-annot.xml");
        HibernateProductService productService = ctx.getBean(HibernateProductService.class);
        productService.printProducts();
    }

    public static void demoJpaSpringXml() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-jpa.xml");
        EntityManagerFactory emf = ctx.getBean(EntityManagerFactory.class);
        PlatformTransactionManager transactionManager = ctx.getBean(PlatformTransactionManager.class);

        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

        EntityManager entityManager = emf.createEntityManager();
        List<Product> products = entityManager.createQuery("select p from Product p").getResultList();
        for (Product product : products) {
            System.out.println(product);
        }

        transactionManager.commit(transactionStatus);
    }

    public static void demoJpaSpringJavaConfig() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(JpaSpringConfig.class);
        JpaProductService productService = ctx.getBean(JpaProductService.class);
        productService.printProducts();
    }

    public static void demoSpringJavaDataJpa() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringDataJpaConfig.class);
        JpaProductService productService = ctx.getBean(JpaProductService.class);
        productService.printProductsStartingWith("a");
    }
}
