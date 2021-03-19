package com.beerhouse.domain.create;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.domain.usecase.create.CreateBeerUseCase;
import com.beerhouse.domain.usecase.create.CreateBeerUseCaseInput;
import com.beerhouse.domain.usecase.create.CreateBeerUseCaseOutput;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateBeerUseCaseTest {

    @Mock
    private BeerRepository beerRepository;

    private CreateBeerUseCase createBeerUseCase;

    private AutoCloseable closeable;

    private static final String NAME = "name-test";
    private static final String INGREDIENTS = "ingredients-test";
    private static final String ALCOHOL_CONTENT = "10%";
    private static final String CATEGORY = "category-test";
    private static final BigDecimal PRICE = BigDecimal.valueOf(100L);
    private static final long ID = 10L;

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

    private CreateBeerUseCase createUseCase() {
        return CreateBeerUseCase
                .builder()
                .beerRepository(beerRepository)
                .build();
    }

    @Before
    public void prepareTest() {
        closeable = MockitoAnnotations.openMocks(this);

        when(beerRepository.save(any(Beer.class)))
                .thenReturn(createBeer());

        createBeerUseCase = createUseCase();
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test(expected = IllegalStateException.class)
    public void shouldValidateName() {
        CreateBeerUseCaseInput input = CreateBeerUseCaseInput.builder()
                .alcoholContent("70%")
                .category("category-test")
                .ingredients("ingredients-test")
                .price(BigDecimal.ONE)
                .build();

        createBeerUseCase.execute(input);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldValidateAlcoholContent() {
        CreateBeerUseCaseInput input = CreateBeerUseCaseInput.builder()
                .name("name-test")
                .category("category-test")
                .ingredients("ingredients-test")
                .price(BigDecimal.ONE)
                .build();

        createBeerUseCase.execute(input);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldValidateCategory() {
        CreateBeerUseCaseInput input = CreateBeerUseCaseInput.builder()
                .name("name-test")
                .alcoholContent("70%")
                .ingredients("ingredients-test")
                .price(BigDecimal.ONE)
                .build();

        createBeerUseCase.execute(input);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldValidateIngredients() {
        CreateBeerUseCaseInput input = CreateBeerUseCaseInput.builder()
                .name("name-test")
                .alcoholContent("70%")
                .category("category-test")
                .price(BigDecimal.ONE)
                .build();

        createBeerUseCase.execute(input);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldValidatePrice() {
        CreateBeerUseCaseInput input = CreateBeerUseCaseInput.builder()
                .name("name-test")
                .alcoholContent("70%")
                .category("category-test")
                .ingredients("ingredients-test")
                .build();

        createBeerUseCase.execute(input);
    }

    @Test
    public void shouldCreateBeer() {
        CreateBeerUseCaseInput input = CreateBeerUseCaseInput.builder()
                .name("name-test")
                .alcoholContent("70%")
                .category("category-test")
                .ingredients("ingredients-test")
                .price(BigDecimal.ONE)
                .build();

        CreateBeerUseCaseOutput output = createBeerUseCase.execute(input);

        Mockito.verify(beerRepository, Mockito.times(1)).save(any(Beer.class));

        Assert.assertEquals(ID, output.getId());
        Assert.assertEquals(NAME, output.getName());
        Assert.assertEquals(INGREDIENTS, output.getIngredients());
        Assert.assertEquals(ALCOHOL_CONTENT, output.getAlcoholContent());
        Assert.assertEquals(CATEGORY, output.getCategory());
        Assert.assertEquals(PRICE, output.getPrice());
    }
}
