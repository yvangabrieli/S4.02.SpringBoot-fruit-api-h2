package cat.itacademy.s04.t02.n01.fruit_api_h2.services;

import cat.itacademy.s04.t02.n01.fruit_api_h2.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit_api_h2.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FruitService {
    Fruit createFruit (Fruit fruit);
    List<Fruit> getAllFruits();
    Fruit getFruitById(Long id);
    Fruit updateFruit(Long id, Fruit fruit);
    void deleteFruit(Long id);

}
