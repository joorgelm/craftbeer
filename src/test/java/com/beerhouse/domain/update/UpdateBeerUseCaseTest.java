package com.beerhouse.domain.update;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.domain.usecase.update.UpdateBeerUseCase;
import com.beerhouse.domain.usecase.update.UpdateBeerUseCaseInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class UpdateBeerUseCaseTest {

    @Mock
    private BeerRepository beerRepository;

    private UpdateBeerUseCase updateBeerUseCase;

    private AutoCloseable closeable;

    private static final String NAME = "name-test";
    private static final String INGREDIENTS = "ingredients-test";
    private static final String ALCOHOL_CONTENT = "10%";
    private static final String CATEGORY = "category-test";
    private static final BigDecimal PRICE = BigDecimal.valueOf(100L);
    private static final long ID = 10L;

    private Beer mockBeer;

    private UpdateBeerUseCase createUseCase() {
        return UpdateBeerUseCase
                .builder()
                .beerRepository(beerRepository)
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

        mockBeer = createBeer();
        when(beerRepository.findById(ID))
                .thenReturn(
                        Optional.of(mockBeer)
                );

        updateBeerUseCase = createUseCase();
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void shouldUpdateName() throws IllegalAccessException {
        UpdateBeerUseCaseInput input = UpdateBeerUseCaseInput.builder()
                .name("new-name")
                .build();

        updateBeerUseCase.update(ID, input);

        mockBeer.setName(input.getName());

        Mockito.verify(beerRepository, Mockito.times(1)).save(mockBeer);
    }

    @Test
    public void shouldUpdatePrice() throws IllegalAccessException {
        UpdateBeerUseCaseInput input = UpdateBeerUseCaseInput.builder()
                .price(BigDecimal.valueOf(200L))
                .build();

        updateBeerUseCase.update(ID, input);

        mockBeer.setPrice(input.getPrice());

        Mockito.verify(beerRepository, Mockito.times(1)).save(mockBeer);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldFailWithNullInput() throws IllegalAccessException {
        UpdateBeerUseCaseInput input = UpdateBeerUseCaseInput.builder()
                .build();

        updateBeerUseCase.update(ID, input);

        mockBeer.setName(input.getName());
    }
}
