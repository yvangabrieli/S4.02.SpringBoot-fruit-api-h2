package cat.itacademy.s04.t02.n01.fruit_api_h2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Setter
@Getter

public class FruitRequestDTO {
    @NotBlank(message = "Fruit name cannot be blank")
    private String name;
    @Positive(message = "Fruit weight must be positive")
    private int weightInKilos;

    public FruitRequestDTO() {
    }

    public FruitRequestDTO(String name, int weightInKilos) {
        this.name = name;
        this.weightInKilos = weightInKilos;
    }
}
