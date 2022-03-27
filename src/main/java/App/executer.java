package App;

import App.Repository.ProductRepository;
import App.Service.ProductService;
import App.Service.Validation.*;
import java.util.HashSet;
import java.util.Set;

public class executer {
    public static void main(String[] args) {
        ProductRepository repository=new ProductRepository();
        ProductNameValidationRule nameValidation=new ProductNameValidationRule();
        ProductPriceRule priceRule=new ProductPriceRule();
        ProductDiscountValidationRule productDiscountValidationRule =new ProductDiscountValidationRule();
        Set<ProductValidationRule> rules=new HashSet<>();
        rules.add(nameValidation);
        rules.add(priceRule);
        rules.add(productDiscountValidationRule);
        ProductValidationService validationService=new ProductValidationService(rules);
        ProductService service=new ProductService(repository,validationService);
        ShoppingListApp shoppingListApplication=new ShoppingListApp(service);
        shoppingListApplication.execute();

    }
}
