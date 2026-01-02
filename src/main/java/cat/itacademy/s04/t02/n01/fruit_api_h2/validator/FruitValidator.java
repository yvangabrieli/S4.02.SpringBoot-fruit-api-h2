package cat.itacademy.s04.t02.n01.fruit_api_h2.validator;

import cat.itacademy.s04.t02.n01.fruit_api_h2.exception.BusinessRuleException;
import cat.itacademy.s04.t02.n01.fruit_api_h2.model.Fruit;
import org.springframework.stereotype.Component;

@Component
public class FruitValidator {

    private static final int MAX_WEIGHT = 1000;
    private static final int MIN_NAME_LENGTH = 4;

    public void validate (Fruit fruit) {
        if (fruit == null){
            throw new IllegalArgumentException ("Fruit cannot be null");
        }
        if (fruit.getName() == null || fruit.getName().isBlank()){
            throw new IllegalArgumentException("Fruit name cannot be empty");
        }
        if (fruit.getWeightInKilos() > MAX_WEIGHT) {
            throw new BusinessRuleException("Fruit weight cannot exceed 1000 kilos");
        }
        if (fruit.getName().length() < MIN_NAME_LENGTH) {
            throw new BusinessRuleException("Fruit name must have at least 4 characters");
        }
    }
}
