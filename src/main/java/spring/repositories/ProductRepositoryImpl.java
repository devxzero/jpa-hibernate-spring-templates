package spring.repositories;

import entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Name must end with "Impl".
 *
 */
public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Product> someCustomRepMethod() {
        // Note, this query is also possible with Spring Data JPAs "Derived Query" technique, which magically implements
        // interface methods based on the name.
        return em.createQuery("select p from Product p where p.name like 'A%'").getResultList();
    }
}
