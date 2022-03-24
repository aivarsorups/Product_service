package main.java.Service.Validation;
import main.java.Domain.Product;
import java.math.BigInteger;
import java.math.BigDecimal;

public class ProductPriceRule implements ProductValidationRule {
    private final BigDecimal MIN_PRICE = new BigDecimal(BigInteger.ZERO);
    public void validate(Product product) {
        checkNotNull(product);
        if(product.getPrice() == null) {
            throw new ProductValidationException("Price cant be null");
        }else if( product.getPrice().compareTo(MIN_PRICE)<=0) {
            throw new ProductValidationException("Prive must be bigger than 0");
        }
    }
}
