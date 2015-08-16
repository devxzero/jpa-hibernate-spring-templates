package spring.services;

import entities.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.repositories.ProductRepository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class JpaProductService {

//    @Autowired
    @Autowired(required = false) // set to false to remove conflicts with other configurations
    private EntityManagerFactory entityManagerFactory;

    @Transactional
    public void printProducts() {
        List<Product> products = entityManagerFactory.createEntityManager().createQuery("select p from Product p").getResultList();
        for (Product product : products) {
            System.out.println(product);
        }
    }


    @Autowired(required = false)
    private ProductRepository productRepository;

    @Transactional
    public void printProductsStartingWith(String start) {
        for (Product product : productRepository.findByNameStartingWith("A")) {
            System.out.println(product);
        }
    }
}
