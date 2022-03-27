package App.Service.Validation;

import App.Domain.Product;

public class ProductNameValidationRule implements ProductValidationRule{
    private final int MIN_LENGTH = 3;
    private final int MAX_LENGTH = 32;

    public void validate(Product product) {
        checkNotNull(product);
        if(product.getName().length() < MIN_LENGTH || product.getName().length() > MAX_LENGTH) {
            throw new ProductValidationException("Product name must be from 3 to 32 sign long");
        }
    }
}
