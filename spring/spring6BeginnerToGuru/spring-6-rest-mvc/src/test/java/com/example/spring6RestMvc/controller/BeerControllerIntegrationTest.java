package com.example.spring6RestMvc.controller;

import com.example.spring6RestMvc.entities.Beer;
import com.example.spring6RestMvc.exception.NotFoundException;
import com.example.spring6RestMvc.mappers.BeerMapper;
import com.example.spring6RestMvc.model.BeerDTO;
import com.example.spring6RestMvc.model.BeerStyle;
import com.example.spring6RestMvc.repositories.BeerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Slf4j
class BeerControllerIntegrationTest {

    @Value("${spring.profiles.active}")
    String activeProfile;

    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    BeerMapper beerMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @PostConstruct
    public void showEnvironment() throws NoSuchFieldException, IllegalAccessException {

        // Show the current implementation the BeerService is using.
        Field serviceField = beerController.getClass().getDeclaredField("beerService");
        serviceField.setAccessible(true);
        log.error("Charles Stockman: The Service class being used is  {}", serviceField.get(beerController).getClass().getCanonicalName());
    }

    @Test
    void testListBeers() {
        List<BeerDTO> dtos = beerController.listBeers();
        log.error("The Beers are: {}", dtos.toString());
        assertThat(dtos.size()).isEqualTo(3);
    }

    @Transactional
    @Rollback
    @Test
    void TestEmptyList() {
        beerRepository.deleteAll();
        List<BeerDTO> dtos = beerController.listBeers();
        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void TestGetById() {
        Beer beer = beerRepository.findAll().getFirst();
        BeerDTO dto = beerController.getBeerById(beer.getId());
        assertThat(dto.getBeerName()).isEqualTo(beer.getBeerName());
    }

    @Test
    void TestGetByIdNotFound() {
        assertThrows(NotFoundException.class, () -> beerController.getBeerById(UUID.randomUUID()));
    }

    @Transactional
    @Rollback
    @Test
    public void saveNewBeerTest() {
        BeerDTO beerDTO = BeerDTO.builder().beerName("New Beer -- chuck").build();
        ResponseEntity<BeerDTO> responseEntity = beerController.handleAPost(beerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID saveUUID = UUID.fromString(locationUUID[4]);

        Optional<Beer> beer = beerRepository.findById(saveUUID);
        assert(beer.isPresent());
    }

    @Test
    @Rollback
    @Transactional
    void updateExistingBeer() {
        Beer beer = beerRepository.findAll().getFirst();
        BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
        beerDTO.setId(null);
        beerDTO.setVersion(null);

        final String beerName = "UPDATED";
        beerDTO.setBeerName(beerName);

        ResponseEntity<BeerDTO> responseEntity = beerController.updateById(beer.getId(), beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        // The beer is stale since we made changes in memory, but not the database.  The fix is to get the original
        // beer from the database
        Beer updatedBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updatedBeer.getBeerName()).isEqualTo(beerName);
    }

    @Test
    void testDeleteByIDNotFound() {
        assertThrows(
                NotFoundException.class,
                () -> beerController.updateById(UUID.randomUUID(), BeerDTO.builder().build()));
    }

    @Test
    @Transactional
    @Rollback
    void deleteBeerByIdFound() {
        Beer beer = beerRepository.findAll().getFirst();

        ResponseEntity beerDTOResponseEntity = beerController.deleteById(beer.getId());
        assertThat(beerDTOResponseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
        assertThat(beerRepository.findById(beer.getId()).isPresent()).isFalse();
    }


    @Test
    public void testPatchWithInvalidId() {
        Beer beer = beerRepository.findAll().getFirst();
        assertThrows( NotFoundException.class, () -> beerController.patchById(UUID.randomUUID(), beerMapper.beerToBeerDTO(beer)));
    }

    @Test
    @Transactional
    @Rollback
    public void testPatchWithValidId() {

        // Expected Beer
        BeerDTO expectedBeer = SerializationUtils.clone(beerController.listBeers().getFirst());
        expectedBeer.setBeerName("PatchName");
        expectedBeer.setBeerStyle(BeerStyle.GOSE);
        expectedBeer.setUpc("PatchUpc");
        expectedBeer.setPrice(new BigDecimal(333));
        expectedBeer.setQuantityOnHand(333);

        beerController.patchById(expectedBeer.getId(), expectedBeer);
        BeerDTO actualBeer = beerController.getBeerById(expectedBeer.getId());
        assertThat(actualBeer).isEqualTo(expectedBeer);

    }

    @Test
    @Transactional
    @Rollback
    public void testPatchWithNValidIdButNoChanges() {

        // Expected Beer
        BeerDTO expectedBeer = SerializationUtils.clone(beerController.listBeers().getFirst());

        beerController.patchById(expectedBeer.getId(), expectedBeer);
        BeerDTO actualBeer = beerController.getBeerById(expectedBeer.getId());
        assertThat(actualBeer).isEqualTo(expectedBeer);

    }

    @Test
    public void testPatchWithValidationError() throws Exception {

        Beer beer = beerRepository.findAll().getFirst();

        Map<String, Object> beerMap = new HashMap<>();
        beerMap.put("beerName", "0123456789012345666666666666666666666667777777777777789012345678901223334134123413414123412");

        MvcResult result = mockMvc.perform(patch("/api/v1/beer/" + beer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(5)))
                .andReturn();

        System.out.println("*******************************************");
        System.out.println(result.getResponse().getContentAsString());
        System.out.println("*******************************************");


    }

}