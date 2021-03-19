package com.beerhouse.domain.delete;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.domain.usecase.delete.DeleteBeerUseCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeleteBeerUseCaseTest {

    @Mock
    private BeerRepository beerRepository;

    private DeleteBeerUseCase deleteBeerUseCase;

    private AutoCloseable closeable;

    private static final String NAME = "name-test";
    private static final String INGREDIENTS = "ingredients-test";
    private static final String ALCOHOL_CONTENT = "10%";
    private static final String CATEGORY = "category-test";
    private static final BigDecimal PRICE = BigDecimal.valueOf(100L);
    private static final Long ID = 10L;

    private Beer mockBeer;

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

    private DeleteBeerUseCase buildUseCase() {
        return DeleteBeerUseCase.builder()
                .beerRepository(beerRepository)
                .build();
    }

    @Before
    public void prepareTest() {
        closeable = MockitoAnnotations.openMocks(this);

        mockBeer = createBeer();

        when(beerRepository.findById(ID))
                .thenReturn(Optional.of(mockBeer));

        deleteBeerUseCase = buildUseCase();
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldFailWithInvalidId() {
        deleteBeerUseCase.execute(20L);
    }

    @Test
    public void shouldDeleteBeer() {
        deleteBeerUseCase.execute(ID);

        Mockito.verify(beerRepository, Mockito.times(1))
                .delete(mockBeer);
    }

}
