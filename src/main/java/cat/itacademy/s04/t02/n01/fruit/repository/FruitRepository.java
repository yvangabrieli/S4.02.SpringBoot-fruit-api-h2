package cat.itacademy.s04.t02.n01.fruit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cat.itacademy.s04.t02.n01.fruit.model.Fruit;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
    boolean existsByName(String name);
}
