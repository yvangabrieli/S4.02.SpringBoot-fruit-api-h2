package cat.itacademy.s04.t02.n01.fruit_api_h2.controllers;

import cat.itacademy.s04.t02.n01.fruit_api_h2.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit_api_h2.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.fruit_api_h2.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit_api_h2.services.FruitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/fruits")
public class FruitController {
    private final FruitService service;

    public FruitController(FruitService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FruitResponseDTO> createFruit(@Valid @RequestBody FruitRequestDTO dto) {
        Fruit fruit = new Fruit(dto.getName(), dto.getWeightInKilos());
        Fruit saved = service.createFruit(fruit);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new FruitResponseDTO(
                        saved.getId(),
                        saved.getName(),
                        saved.getWeightInKilos()));
    }

    @GetMapping
    public List<FruitResponseDTO> getALl() {
        return service.getAllFruits()
                .stream()
                .map(f -> new FruitResponseDTO(
                        f.getId(), f.getName(), f.getWeightInKilos()))
                .toList();
    }

    @GetMapping("/{id}")
    public FruitResponseDTO getById(@PathVariable Long id) {
        Fruit fruit = service.getFruitById(id);
        return new FruitResponseDTO(
                fruit.getId(),
                fruit.getName(),
                fruit.getWeightInKilos());
    }

    @PutMapping("/{id}")
    public FruitResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody FruitRequestDTO dto) {
        Fruit updated = service.updateFruit(
                id,
                new Fruit(dto.getName(), dto.getWeightInKilos()));
        return new FruitResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getWeightInKilos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteFruit(id);
        return ResponseEntity.noContent().build();
    }
}
