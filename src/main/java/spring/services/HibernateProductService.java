package spring.services;

import entities.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HibernateProductService {

//    @Autowired
    @Autowired(required = false) // set to false to remove conflicts with other configurations
    private SessionFactory sessionFactory;

    @Transactional
    public void printProducts() {
        List<Product> products = sessionFactory.getCurrentSession().createQuery("from Product").list();
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
