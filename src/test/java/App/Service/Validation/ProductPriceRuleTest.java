package App.Service.Validation;

import App.Domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.math.BigDecimal;

public class ProductPriceRuleTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductPriceRule victim = new ProductPriceRule();
    private Product input;

    @Test
    public void validateNegativePrice() {
        input = victimProduct(new BigDecimal(-1));
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Price must be bigger than 0");
        victim.validate(input);
    }
    @Test
    public void validate0Price() {
        input = victimProduct(new BigDecimal(0));
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Price must be bigger than 0");
        victim.validate(input);
    }
    @Test
    public void validatePriceOk() {
        input = victimProduct(new BigDecimal(20));
        victim.validate(input);
    }


    private Product victimProduct(BigDecimal number) {
        Product product = new Product();
        product.setPrice(number);
        return product;
    }
}