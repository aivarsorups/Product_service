package App.Service.Validation;

import App.Domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProductNameValidationRuleTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private ProductNameValidationRule victim = new ProductNameValidationRule();
    private Product input;

    private Product victimProduct(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }

    @Test
    public void checkNameLowerThan3Signs() {
        input = victimProduct("Vi");
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product name must be from 3 to 32 sign long");
        victim.validate(input);
    }
    @Test
    public void checkNameBiggerThan32Signs() {
        input = victimProduct("qwertyuioplkjhgfdsazxcvbnmmnbvcxza");
        expectedException.expect(ProductValidationException.class);
        expectedException.expectMessage("Product name must be from 3 to 32 sign long");
        victim.validate(input);
    }
    @Test
    public void checkNameIsOk() {
        input = victimProduct("qwertyuioplkjhgfdsazxcv");
        victim.validate(input);
    }


}