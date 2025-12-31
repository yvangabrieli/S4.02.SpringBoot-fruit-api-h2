package cat.itacademy.s04.t02.n01.fruit_api_h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cat.itacademy.s04.t02.n01.fruit_api_h2.model.Fruit;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
}
