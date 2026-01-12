package cat.itacademy.s04.t02.n01.fruit.mapper;

import cat.itacademy.s04.t02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.fruit.model.Fruit;
import org.springframework.stereotype.Component;

@Component
public class FruitMapper {
    public Fruit toEntity(FruitRequestDTO dto) {
        return new Fruit(dto.getName(), dto.getWeightInKilos());
    }

    public FruitResponseDTO toDTO(Fruit fruit) {
        return new FruitResponseDTO(
                fruit.getId(),
                fruit.getName(),
                fruit.getWeightInKilos()
        );
    }
}
