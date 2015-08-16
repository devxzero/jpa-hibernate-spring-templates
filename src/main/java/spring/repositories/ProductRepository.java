package spring.repositories;

import entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//public interface ProductRepository extends CrudRepository<Product, Long> {
public interface ProductRepository extends CrudRepository<Product, Long>, ProductRepositoryCustom {
    List<Product> findByNameStartingWith(String start);
}
