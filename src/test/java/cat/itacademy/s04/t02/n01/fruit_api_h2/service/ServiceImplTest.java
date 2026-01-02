package cat.itacademy.s04.t02.n01.fruit_api_h2.service;

import cat.itacademy.s04.t02.n01.fruit_api_h2.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit_api_h2.repository.FruitRepository;
import cat.itacademy.s04.t02.n01.fruit_api_h2.services.FruitService;
import cat.itacademy.s04.t02.n01.fruit_api_h2.services.FruitServiceImpl;
import cat.itacademy.s04.t02.n01.fruit_api_h2.validator.FruitValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ServiceImplTest {

    private final FruitRepository repository = Mockito.mock(FruitRepository.class);
    private final FruitValidator validator = Mockito.mock(FruitValidator.class);

    private final FruitService service = new FruitServiceImpl(repository, validator);

    @Test
    void shouldCreateFruitSuccessfully(){
        Fruit fruit = new Fruit("Apple", 2);

        Mockito.when(repository.save(fruit)).thenReturn(fruit);

        Fruit result = service.createFruit(fruit);

        assertEquals ("Apple", result.getName());
        assertEquals (2, result.getWeightInKilos());
    }
}
