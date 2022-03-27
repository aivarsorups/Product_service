package App.Service;

import App.Domain.Product;
import App.Repository.ProductRepository;
import App.Service.Validation.ProductValidationService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;
    @Mock
    private ProductValidationService validationService;
    @InjectMocks
    private ProductService victim;
    @Captor
    private ArgumentCaptor<Product> productCaptor;

    private Product product() {
        Product product = new Product();
        product.setName("TEST_NAME");
        product.setPrice(new BigDecimal(25));
        product.setDiscount(new BigDecimal(20));
        product.setId(100L);
        return product;
    }
    @Test
    public void createProduct() {
        Product product = product();
        when(repository.save(product)).thenReturn(product);
        Long result = victim.createProduct(product);
        verify(validationService).validate(productCaptor.capture());
        Product captorResult = productCaptor.getValue();
        assertThat(captorResult).isEqualTo(product);
        assertThat(product.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindProductById() {
        when(repository.findById(100L)).thenReturn(Optional.of(product()));
        Product result = victim.findById(100L);
        assertThat(result).isEqualTo(product());
    }

    @Test
    public void shouldThrowExceptionProductNotFound() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> victim.findById(100L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Product not found, id100");
    }

}
