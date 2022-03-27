package App.Repository;

import App.Domain.Product;
import java.util.List;
import java.util.Optional;

public interface RepositoryInterface {
    Product save(Product product);
    Optional<Product> findById(Long id);
    Product update(Product product);
    void delete(Long id);
    List<Product> findAllProducts();


}
