package cat.itacademy.s04.t02.n01.fruit_api_h2.dto;

import lombok.*;

@Getter
@Setter
public class FruitResponseDTO {
    private Long id;
    private String name;
    private int weightInKilos;

    public FruitResponseDTO(Long id, String name, int weightInKilos) {
        this.id = id;
        this.name = name;
        this.weightInKilos = weightInKilos;
    }
}
