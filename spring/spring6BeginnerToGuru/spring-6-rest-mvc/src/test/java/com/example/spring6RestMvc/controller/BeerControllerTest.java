package com.example.spring6RestMvc.controller;

import com.example.spring6RestMvc.model.BeerDTO;
import com.example.spring6RestMvc.model.BeerStyle;
import com.example.spring6RestMvc.service.BeerService;
import com.example.spring6RestMvc.service.serviceImplementaitons.BeerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// A test splice limited to the BeerController classed instead of all classes
// Allows management of this class only
@WebMvcTest(BeerController.class)
@Slf4j
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    // Asking SpringBootContext to bring in the AutoMapper (configured with sensible defaults)
    @Autowired
    ObjectMapper objectMapper;

    // Provide the mock of the BeerService.  Provides back a null response by default.
    @MockBean
    BeerService beerService;

    BeerServiceImpl beerServiceImpl;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<BeerDTO> beerArgumentCaptor;

    @BeforeEach
    void setUp() {
        beerServiceImpl = new BeerServiceImpl();
    }

    @Test
    void testGetBeerById() throws Exception {
        BeerDTO testBeer = beerServiceImpl.listBeers().getFirst();

        // Two different ways to write the same line
        // given(beerService.getBeerById(any(UUID.class))).willReturn(testBeer);
        given(beerService.getBeerById(testBeer.getId())).willReturn(Optional.of(testBeer));


        mockMvc.perform(get("/api/v1/beer/" + testBeer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", is(testBeer.getBeerName())));
    }

    @Test
    public void testListBeers() throws Exception {
        given(beerService.listBeers()).willReturn(beerServiceImpl.listBeers());

        mockMvc.perform(get("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));

    }

    @Test
    void testCreateNewBeer() throws Exception {

        // Object Mapper -- Serialize and Deserialize from JSON to POJO
        // Jackson has modules and findAndRegisterModules -- tell jackson to look on the classpath for modules
        // have function writeValueAsString
        objectMapper.findAndRegisterModules();

        BeerDTO beer = beerServiceImpl.listBeers().getFirst();
        beer.setId(null);
        beer.setVersion(null);

        // Returning the second object so that we have a completed object being returned.
        given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn(beerServiceImpl.listBeers().get(1));

        mockMvc.perform(post("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location")).andReturn();
    }

    @Test
    void testUpdateBeer() throws Exception {
        BeerDTO beer = beerServiceImpl.listBeers().getFirst();

        given(beerService.updateBeerById(any(), any())).willReturn(Optional.of(beer));

        mockMvc.perform(put("/api/v1/beer/" + beer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isNoContent());

        // Verify the object and method have been called.
        verify(beerService).updateBeerById(any(UUID.class), any(BeerDTO.class));

    }

    @Test
    void testDeleteBeer() throws Exception {
        BeerDTO beer = beerServiceImpl.listBeers().getFirst();

        given(beerService.deleteById(any())).willReturn(true);

        mockMvc.perform(delete("/api/v1/beer/" + beer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(beerService).deleteById(any());

        ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);

        // Verify the object and method have been called.
        verify(beerService).deleteById(uuidArgumentCaptor.capture());
        assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    public void testPatchBeer() throws Exception {
        BeerDTO beer = beerServiceImpl.listBeers().getFirst();

        Map<String, Object> beerMap = new HashMap<>();
        beerMap.put("beerName", "New Name");

        given(beerService.patchById(any(), any())).willReturn( Optional.of(beer) );


        mockMvc.perform( patch("/api/v1/beer/" + beer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beerMap))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(beerService).patchById(uuidArgumentCaptor.capture(), beerArgumentCaptor.capture());
        assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(beerMap.get("beerName")).isEqualTo(beerArgumentCaptor.getValue().getBeerName());

    }

    @Test
    public void getBeerByIdNotFound() throws Exception {
        given(beerService.getBeerById(any(UUID.class))).willReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID())).andExpect(status().isNotFound());

    }

    @Test
    void testCreateNullBeerName() throws Exception {

        BeerDTO beerDTO = BeerDTO.builder().build();

        given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn(beerServiceImpl.listBeers().getFirst());

        MvcResult mvcResult = mockMvc.perform( post("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beerDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.length()",is(6)))
        .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testCreateBeerNullBeerName() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().build();

        given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn(beerServiceImpl.listBeers().getFirst());

        mockMvc.perform(post("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beerDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateBeerWhiteSpaceBeerName() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().build();
        beerDTO.setBeerName("   ");


        given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn(beerServiceImpl.listBeers().getFirst());

        mockMvc.perform(post("/api/v1/beer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateBeerEmptyBeerName() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().build();
        beerDTO.setBeerName("");


        given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn(beerServiceImpl.listBeers().getFirst());

        mockMvc.perform(post("/api/v1/beer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testConstrainEmptyBeerStyle() throws  Exception {
        BeerDTO beerDTO = BeerDTO.builder().beerName("Test 1").upc("test 1").price(new BigDecimal("50.2")).build();

        given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn((beerServiceImpl.listBeers().getFirst()));

        mockMvc.perform(post("/api/v1/beer")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(beerDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testConstraintEmptyUPC() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().beerName("Test 1").beerStyle(BeerStyle.ALE).price(new BigDecimal("50.2")).build();

        given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn((beerServiceImpl.listBeers().getFirst()));

        mockMvc.perform(post("/api/v1/beer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testConstraintEmptyPrice() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().beerName("Test 1").beerStyle(BeerStyle.ALE).upc("test1").build();
        //beerDTO.setBeerName(null);

        given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn((beerServiceImpl.listBeers().getFirst()));

        mockMvc.perform(post("/api/v1/beer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(("$.length()"),is(1)));
    }

    @Test
    void testConstraintUPCIsBlank() throws Exception {
        BeerDTO beerDTO = beerServiceImpl.listBeers().getFirst();
        beerDTO.setUpc("");

        given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn((beerServiceImpl.listBeers().getFirst()));

        log.error("The beer is {}", beerDTO);
        log.error("The beer is {}", objectMapper.writeValueAsString(beerDTO));

        mockMvc.perform(post("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beerDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void testConstraintUPCIsWhiteSpace() throws Exception {
        BeerDTO beerDTO = beerServiceImpl.listBeers().getFirst();
        beerDTO.setUpc("    ");

        given(beerService.saveNewBeer(any(BeerDTO.class))).willReturn((beerServiceImpl.listBeers().getFirst()));

        mockMvc.perform(post("/api/v1/beer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }



}