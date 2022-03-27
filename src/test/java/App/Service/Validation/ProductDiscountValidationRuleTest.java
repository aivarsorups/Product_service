package App.Service.Validation;

import App.Domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class ProductDiscountValidationRuleTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductDiscountValidationRule victim = new ProductDiscountValidationRule();
    private Product input;

    private Product victimProduct(BigDecimal discount, BigDecimal price ) {
        Product product = new Product();
        product.setPrice(price);
        product.setDiscount(discount);
        return product;
    }

    @Test
    public void checkNullReturnMsg() {
        input = victimProduct(null, new BigDecimal(50));
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Discount cant be null");
        victim.validate(input);
    }
    @Test
    public void checkNegativeReturnMsg() {
        input = victimProduct(new BigDecimal(-20), new BigDecimal(50));
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product discount cant be negative");
        victim.validate(input);
    }
    @Test
    public void checkMoreThan100ProcReturnMsg() {
        input = victimProduct(new BigDecimal(200), new BigDecimal(100));
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product discount cant be more than 100%");
        victim.validate(input);
    }
    @Test
    public void checkIfPriceLowerThan20ReturnMsg() {
        input = victimProduct(new BigDecimal(10), new BigDecimal(19));
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product cant have discount if price is lower than 20$");
        victim.validate(input);
    }
    @Test
    public void checkIfPriceOkAndDiscountOk() {
        input = victimProduct(new BigDecimal(10), new BigDecimal(20));
        victim.validate(input);
    }
}