package spring.repositories;

import entities.Product;

import java.util.List;

/**
 * Need when adding custom repository methods.
 */
public interface ProductRepositoryCustom {
    List<Product> someCustomRepMethod();
}
