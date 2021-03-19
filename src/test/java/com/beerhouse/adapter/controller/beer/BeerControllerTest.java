package com.beerhouse.adapter.controller.beer;

import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.domain.usecase.create.CreateBeerUseCaseInput;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BeerRepository beerRepository;

    @After
    public void afterTest() {
        beerRepository.deleteAll();
    }

    @Test
    public void shouldInsertBeer() throws Exception {

        CreateBeerUseCaseInput input = CreateBeerUseCaseInput
                .builder()
                .name("name-test")
                .category("category-test")
                .ingredients("ingredients-test")
                .price(BigDecimal.TEN)
                .alcoholContent("alcoholContent-test")
                .build();

        String requestBody = new Gson().toJson(input);

        ResultActions response = mockMvc.perform(post("/beers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        response.andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("id")))
                .andExpect(content().string(containsString("name-test")))
                .andExpect(content().string(containsString("category-test")))
                .andExpect(content().string(containsString("ingredients-test")))
                .andExpect(content().string(containsString("alcoholContent-test")))
                .andExpect(content().string(containsString("10")));
    }

    @Test
    @Sql({"/datasets/beer-test.sql"})
    public void shouldListBeer() throws Exception {

        ResultActions response = mockMvc.perform(get("/beers/"));

        response.andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("Red Ale")))
                .andExpect(content().string(containsString("Americam Ale")))
                .andExpect(content().string(containsString("Americam Ale")))
                .andExpect(content().string(containsString("20%")));
    }
}
