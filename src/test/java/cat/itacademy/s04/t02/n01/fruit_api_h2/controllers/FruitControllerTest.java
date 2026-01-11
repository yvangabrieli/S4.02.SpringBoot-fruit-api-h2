package cat.itacademy.s04.t02.n01.fruit_api_h2.controllers;

import cat.itacademy.s04.t02.n01.fruit_api_h2.dto.FruitRequestDTO;
import cat.itacademy.s04.t02.n01.fruit_api_h2.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n01.fruit_api_h2.mapper.FruitMapper;
import cat.itacademy.s04.t02.n01.fruit_api_h2.model.Fruit;
import cat.itacademy.s04.t02.n01.fruit_api_h2.services.FruitService;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;



import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@WebMvcTest(FruitController.class)
@Import(FruitMapper.class)
class FruitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FruitService service;

    @Autowired
    private ObjectMapper objectMapper;

    // ---------------- CREATE ----------------
    @Test
    void shouldCreateFruit() throws Exception {
        FruitRequestDTO requestDTO = new FruitRequestDTO("Apple", 2);
        Fruit saved = new Fruit("Apple", 2);
        saved.setId(1L);


        when(service.createFruit(any(Fruit.class))).thenReturn(saved);


        mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Apple"))
                .andExpect(jsonPath("$.weightInKilos").value(2));
    }

    // ---------------- GET ALL ----------------
    @Test
    void shouldReturnAllFruits() throws Exception {
        Fruit fruit = new Fruit("Apple", 2);
        fruit.setId(1L);

        when(service.getAllFruits()).thenReturn(List.of(fruit));

        mockMvc.perform(get("/fruits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Apple"))
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    // ---------------- GET BY ID ----------------
    @Test
    void shouldReturnFruitById() throws Exception {
        Fruit fruit = new Fruit( "Apple", 2);
        fruit.setId(1L);

        when(service.getFruitById(1L)).thenReturn(fruit);

        mockMvc.perform(get("/fruits/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Apple"));
    }

    // ---------------- UPDATE ----------------
    @Test
    void shouldUpdateFruit() throws Exception {
        FruitRequestDTO requestDTO = new FruitRequestDTO("Orange", 5);
        Fruit update = new Fruit( "Orange", 5);
        update.setId(1L);


        when(service.updateFruit(anyLong(),any(Fruit.class))).thenReturn(update);

        mockMvc.perform(put("/fruits/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Orange"))
                .andExpect(jsonPath("$.weightInKilos").value(5));
    }

    // ---------------- DELETE ----------------
    @Test
    void shouldDeleteFruit() throws Exception {
        mockMvc.perform(delete("/fruits/1"))
                .andExpect(status().isNoContent());
    }

    // ---------------- VALIDATION ----------------
    @Test
    void shouldReturn400WhenInvalidInput() throws Exception {
        FruitRequestDTO invalid = new FruitRequestDTO("", -1);

        mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }
}
