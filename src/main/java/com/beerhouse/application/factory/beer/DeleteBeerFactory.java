package com.beerhouse.application.factory.beer;

import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.domain.usecase.delete.DeleteBeerUseCase;
import org.springframework.context.annotation.Bean;

public class DeleteBeerFactory {

    @Bean
    public DeleteBeerUseCase factoryDeleteBeerUseCase(BeerRepository beerRepository) {
        return DeleteBeerUseCase.builder()
                .beerRepository(beerRepository)
                .build();
    }
}
