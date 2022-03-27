package App.Service.Validation;

import App.Domain.Product;
import java.math.BigDecimal;

public class ProductDiscountValidationRule implements ProductValidationRule {
    private static BigDecimal MIN_DISCOUNT = new BigDecimal(0);
    private static BigDecimal MAX_DISCOUNT = new BigDecimal(100);
    private static BigDecimal PRODUCT_LOWEST_PRICE_FOR_DISCOUNT = new BigDecimal(20);

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getDiscount() == null) {
            throw new ProductValidationException("Discount cant be null");
        }
        if (product.getDiscount().compareTo(MIN_DISCOUNT) < 0) {
            throw new ProductValidationException("Product discount cant be negative");
        }
        if (product.getDiscount().compareTo(MAX_DISCOUNT) > 0) {
            throw new ProductValidationException("Product discount cant be more than 100%");
        }
        if (product.getPrice().compareTo(PRODUCT_LOWEST_PRICE_FOR_DISCOUNT) < 0 && product.getDiscount().compareTo(MIN_DISCOUNT) != 0 ) {
            throw new ProductValidationException("Product cant have discount if price is lower than 20$");
        }
    }

}
