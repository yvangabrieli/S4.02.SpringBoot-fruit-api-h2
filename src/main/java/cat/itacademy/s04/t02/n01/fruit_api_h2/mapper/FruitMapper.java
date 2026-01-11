package cat.itacademy.s04.t02.n01.fruit_api_h2.mapper;

import cat.itacademy.s04.t02.n01.fruit_api_h2.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit_api_h2.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.fruit_api_h2.model.Fruit;
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
