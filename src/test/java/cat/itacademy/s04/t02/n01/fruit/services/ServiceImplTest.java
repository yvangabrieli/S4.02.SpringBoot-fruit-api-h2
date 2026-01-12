package cat.itacademy.s04.t02.n01.fruit.services;

import cat.itacademy.s04.t02.n01.fruit.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n01.fruit.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit.repository.FruitRepository;
import cat.itacademy.s04.t02.n01.fruit.validator.FruitValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ServiceImplTest {

    private final FruitRepository repository = Mockito.mock(FruitRepository.class);
    private final FruitValidator validator = Mockito.mock(FruitValidator.class);

    private final FruitService service = new FruitServiceImpl(repository, validator);

    // ---------------- CREATE ----------------
    @Test
    void shouldCreateFruitSuccessfully() {
        Fruit fruit = new Fruit( "Apple", 2);

        when(repository.save(fruit)).thenReturn(fruit);

        Fruit result = service.createFruit(fruit);

        verify(validator).validate(fruit); // business validation applied
        assertEquals("Apple", result.getName());
        assertEquals(2, result.getWeightInKilos());
    }

    // ---------------- GET ALL ----------------
    @Test
    void shouldReturnAllFruits() {
        List<Fruit> fruits = List.of(
                new Fruit("Apple", 2),
                new Fruit("Banana", 3)
        );

        when(repository.findAll()).thenReturn(fruits);

        List<Fruit> result = service.getAllFruits();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    // ---------------- GET BY ID ----------------
    @Test
    void shouldReturnFruitById() {
        Fruit fruit = new Fruit("Apple", 2);

        when(repository.findById(1L)).thenReturn(Optional.of(fruit));

        Fruit result = service.getFruitById(1L);

        assertEquals("Apple", result.getName());
        verify(repository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenFruitNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FruitNotFoundException.class,
                () -> service.getFruitById(1L));
    }

    // ---------------- UPDATE ----------------
    @Test
    void shouldUpdateFruitSuccessfully() {
        Fruit existing = new Fruit("Apple", 2);
        Fruit updated = new Fruit( "Orange", 5);

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        Fruit result = service.updateFruit(1L, updated);

        assertEquals("Orange", result.getName());
        assertEquals(5, result.getWeightInKilos());
        verify(validator).validate(updated);
    }

    // ---------------- DELETE ----------------
    @Test
    void shouldDeleteFruitSuccessfully() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteFruit(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingFruit() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(FruitNotFoundException.class,
                () -> service.deleteFruit(1L));
    }
}