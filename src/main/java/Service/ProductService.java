package main.java.Service;

import main.java.Domain.Product;
import main.java.Repository.ProductRepository;
import main.java.Repository.RepositoryInterface;
import main.java.Service.Validation.ProductValidationService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ProductService {
    private ProductRepository repository;
    private ProductValidationService validationService;

    public ProductService(ProductRepository repository,
                          ProductValidationService validationService){
        this.repository=repository;
        this.validationService=validationService;
    }
    public Long createProduct(Product product) {
        validationService.validate(product);
        Product createProduct = repository.save(product);
        return createProduct.getId();
    }
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Product not found, id" + id
        ));
    }
    public void delete(Long id) {
        repository.delete(id);
    }
    public Product updateProduct(Product product) {
        findById(product.getId());
        return repository.update(product);
    }
    public List<Product> findAllProducts() {
        return repository.findAllProducts();
    }

}
