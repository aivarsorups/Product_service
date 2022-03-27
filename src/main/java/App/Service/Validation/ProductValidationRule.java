package App.Service.Validation;

import App.Domain.Product;

public interface ProductValidationRule {
    void validate(Product product);

    default void checkNotNull(Product product) {
        if(product == null) {
            throw new ProductValidationException("Product must be not null");
        }
    }
}
