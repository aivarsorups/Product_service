package main.java.Service.Validation;
import main.java.Domain.Product;

import java.util.Set;

public class ProductValidationService {
    private final Set<ProductValidationRule> validationRules;

    public ProductValidationService(Set<ProductValidationRule> validationRules) {
        this.validationRules = validationRules;
    }
    public void validate(Product product) {
        validationRules.forEach(rule->rule.validate(product));
    }
}
