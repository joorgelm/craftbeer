package com.beerhouse.domain.get;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.domain.usecase.get.GetBeerUseCase;
import com.beerhouse.domain.usecase.get.GetBeerUseCaseOutputList;
import com.beerhouse.domain.usecase.get.GetBeerUseCaseOutputSingle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
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


    private GetBeerUseCase buildUseCase() {
        return GetBeerUseCase.builder()
                .beerRepository(beerRepository)
                .build();
    }

    private List<Beer> createBeerList(int size) {
        List<Beer> beerList = new java.util.ArrayList<>();

        for (int i = 0; i < size; i++)
            beerList.add(createBeer(i));

        return beerList;
    }

    private Beer createBeer(Integer counter) {
        return Beer.builder()
                .id(ID + counter)
                .name(NAME + counter)
                .ingredients(INGREDIENTS + counter)
                .category(CATEGORY + counter)
                .alcoholContent(ALCOHOL_CONTENT + counter)
                .price(PRICE.add(BigDecimal.valueOf(counter)))
                .build();
    }

    private Beer createBeer() {
        return Beer.builder()
                .id(ID)
                .name(NAME)
                .ingredients(INGREDIENTS)
                .category(CATEGORY)
                .alcoholContent(ALCOHOL_CONTENT)
                .price(PRICE)
                .build();
    }

    @Before
    public void prepareTest() {
        closeable = MockitoAnnotations.openMocks(this);

        getBeerUseCase = buildUseCase();
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void shouldGetAllBeers() {
        when(beerRepository.findAll())
                .thenReturn(createBeerList(2));

        GetBeerUseCaseOutputList getBeerUseCaseOutput = (GetBeerUseCaseOutputList) getBeerUseCase.get(null);
        List<Beer> beers = getBeerUseCaseOutput.getBeers();
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

        GetBeerUseCaseOutputSingle getBeerUseCaseOutput = (GetBeerUseCaseOutputSingle) getBeerUseCase.get(ID);
        Beer beer = getBeerUseCaseOutput.getBeer();


        Assert.assertEquals(ID, Long.valueOf(beer.getId()));
        Assert.assertEquals(NAME, beer.getName());
        Assert.assertEquals(INGREDIENTS, beer.getIngredients());
        Assert.assertEquals(CATEGORY, beer.getCategory());
        Assert.assertEquals(ALCOHOL_CONTENT, beer.getAlcoholContent());
        Assert.assertEquals(PRICE, beer.getPrice());
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldFailWithInvalidId() {
        getBeerUseCase.get(20L);
    }
}
