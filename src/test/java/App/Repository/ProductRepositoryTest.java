package App.Repository;

import App.Domain.Product;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ProductRepositoryTest {
    private static final String PRODUCT_NAME = "TEST_NAME";
    private static final String PRODUCT_DESCRIPTION = "TEST_DESCRIPTION";
    private static final long PRODUCT_ID = 0L;
    private static final BigDecimal PRODUCT_PRICE = new BigDecimal(100);
    private static final BigDecimal PRODUCT_DISCOUNT = new BigDecimal(50);
    private ProductRepository victim = new ProductRepository();
    private Product product = product();

    private Product product() {
        Product product = new Product();
        product.setName(PRODUCT_NAME);
        product.setPrice(PRODUCT_PRICE);
        product.setDiscount(PRODUCT_DISCOUNT);
        product.setDescription(PRODUCT_DESCRIPTION);
        return product;
    }
    private Product expectedProduct() {
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setName(PRODUCT_NAME);
        product.setPrice(PRODUCT_PRICE);
        product.setDiscount(PRODUCT_DISCOUNT);
        product.setDescription(PRODUCT_DESCRIPTION);
        return product;
    }


    @Test
    public void save() {
        Product result = victim.save(product());
        assertThat(result).isEqualTo(expectedProduct());
    }

    @Test
    public void findById() {
        victim.save(product);
        Optional<Product> result = victim.findById(PRODUCT_ID);
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedProduct());
    }

    @Test
    public void update() {
        Product saveProduct = victim.save(product());
        Product result = victim.update(saveProduct);
        assertThat(result).isEqualTo(expectedProduct());
    }

    @Test
    public void delete() {
        Product result=victim.save(product);
        assertThat(result).isEqualTo(expectedProduct());
        victim.delete(0L);
        Optional<Product> optResult = victim.findById(0L);
        assertThat(optResult).isEqualTo(Optional.empty());
    }

    @Test
    public void findAllProducts() {
        List<Product> result = new ArrayList<>();
        result.add(expectedProduct());
        victim.save(product());
        List<Product> ExpectedResult = victim.findAllProducts();
        assertThat(result).isEqualTo(ExpectedResult);

    }
}
