package com.beerhouse.domain.get;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.domain.usecase.get.GetBeerUseCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class GetBeerUseCaseTest {

    @Mock
    private BeerRepository beerRepository;

    private GetBeerUseCase getBeerUseCase;

    private AutoCloseable closeable;

    private static final String NAME = "name-test";
    private static final String INGREDIENTS = "ingredients-test";
    private static final String ALCOHOL_CONTENT = "10%";
    private static final String CATEGORY = "category-test";
    private static final BigDecimal PRICE = BigDecimal.valueOf(100L);
    private static final Long ID = 10L;

    private int beerMockCounter;


    private GetBeerUseCase buildUseCase() {
        return GetBeerUseCase.builder()
                .beerRepository(beerRepository)
                .build();
    }

    private Beer createBeer() {
        Beer beer = Beer.builder()
                .id(ID + beerMockCounter)
                .name(NAME + beerMockCounter)
                .ingredients(INGREDIENTS + beerMockCounter)
                .category(CATEGORY + beerMockCounter)
                .alcoholContent(ALCOHOL_CONTENT + beerMockCounter)
                .price(PRICE.add(BigDecimal.valueOf(beerMockCounter)))
                .build();

        beerMockCounter++;
        return beer;
    }

    @Before
    public void prepareTest() {
        closeable = MockitoAnnotations.openMocks(this);

        beerMockCounter = 0;

        getBeerUseCase = buildUseCase();
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void shouldGetAllBeers() {
        when(beerRepository.findAll())
                .thenReturn(Arrays.asList(createBeer(), createBeer()));

        List<Beer> beers = getBeerUseCase.get(null).getBeers();
        int size = beers.size();

        Assert.assertEquals(2, size);

        int i = 0;

        for (Beer beer: beers) {
            Assert.assertEquals(ID + i, beer.getId());
            Assert.assertEquals(NAME + i, beer.getName());
            Assert.assertEquals(INGREDIENTS + i, beer.getIngredients());
            Assert.assertEquals(CATEGORY + i, beer.getCategory());
            Assert.assertEquals(ALCOHOL_CONTENT + i, beer.getAlcoholContent());
            Assert.assertEquals(PRICE.add(BigDecimal.valueOf(i)), beer.getPrice());
            i++;
        }
    }

    @Test
    public void shouldGetBeerById() {

        when(beerRepository.findById(ID))
                .thenReturn(Optional.of(createBeer()));

        List<Beer> beers = getBeerUseCase.get(ID).getBeers();
        int size = beers.size();

        Beer beer = beers.get(0);

        int i = 0;

        Assert.assertEquals(1, size);
        Assert.assertEquals(ID + i, beer.getId());
        Assert.assertEquals(NAME + i, beer.getName());
        Assert.assertEquals(INGREDIENTS + i, beer.getIngredients());
        Assert.assertEquals(CATEGORY + i, beer.getCategory());
        Assert.assertEquals(ALCOHOL_CONTENT + i, beer.getAlcoholContent());
        Assert.assertEquals(PRICE.add(BigDecimal.valueOf(i)), beer.getPrice());
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldFailWithInvalidId() {
        getBeerUseCase.get(20L);
    }
}
