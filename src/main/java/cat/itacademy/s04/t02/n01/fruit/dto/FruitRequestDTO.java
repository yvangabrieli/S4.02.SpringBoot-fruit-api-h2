package cat.itacademy.s04.t02.n01.fruit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class FruitRequestDTO {
    @NotBlank(message = "Fruit name cannot be blank")
    private String name;
    @Positive(message = "Fruit weight must be positive")
    private int weightInKilos;
}
