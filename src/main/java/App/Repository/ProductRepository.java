package App.Repository;

import App.Domain.Product;
import java.util.*;

public class ProductRepository implements RepositoryInterface {
    private Long productIdSequance = 0L;
    private Map<Long, Product> repository =  new HashMap<>();

    @Override
    public Product save(Product product) {
        product.setId(productIdSequance++);
        repository.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public Product update(Product product) {
        repository.put(product.getId(), product);
        return product;
    }

    @Override
    public void delete(Long id) {
        repository.remove(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return new ArrayList<>(repository.values());
    }
}
