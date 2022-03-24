package main.java;

import main.java.Repository.ProductRepository;
import main.java.Service.ProductService;
import main.java.Service.Validation.*;
import main.java.ShoppingListApp;

import java.util.HashSet;
import java.util.Set;

public class executer {
    public static void main(String[] args) {
        ProductRepository repository=new ProductRepository();
        ProductNameValidationRule nameValidation=new ProductNameValidationRule();
        ProductPriceRule priceRule=new ProductPriceRule();
        DiscountValidationRule discountValidationRule=new DiscountValidationRule();
        Set<ProductValidationRule> rules=new HashSet<>();
        rules.add(nameValidation);
        rules.add(priceRule);
        rules.add(discountValidationRule);
        ProductValidationService validationService=new ProductValidationService(rules);
        ProductService service=new ProductService(repository,validationService);
        ShoppingListApp shoppingListApplication=new ShoppingListApp(service);
        shoppingListApplication.execute();

    }
}
