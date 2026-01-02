package cat.itacademy.s04.t02.n01.fruit_api_h2.services;

import cat.itacademy.s04.t02.n01.fruit_api_h2.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n01.fruit_api_h2.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit_api_h2.repository.FruitRepository;
import cat.itacademy.s04.t02.n01.fruit_api_h2.validator.FruitValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitServiceImpl implements FruitService {
    private final FruitRepository repository;
    private final FruitValidator validator;

    public FruitServiceImpl(FruitRepository repository, FruitValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Fruit createFruit(Fruit fruit) {
        validator.validate(fruit);
        return repository.save(fruit);
    }

    @Override
    public List<Fruit> getAllFruits() {
        return repository.findAll();
    }

    @Override
    public Fruit getFruitById(Long id) {
        return repository.findById(id).orElseThrow(() -> new FruitNotFoundException(id));
    }

    @Override
    public Fruit updateFruit(Long id, Fruit fruit) {
        Fruit existing = getFruitById(id);
        validator.validate(fruit);
        existing.setName(fruit.getName());
        existing.setWeightInKilos(fruit.getWeightInKilos());
        return repository.save(existing);
    }

    @Override
    public void deleteFruit(Long id) {
        if (!repository.existsById(id)) {
            throw new FruitNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
