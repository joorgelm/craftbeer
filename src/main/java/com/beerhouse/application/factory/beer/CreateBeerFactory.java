package com.beerhouse.application.factory.beer;

import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.domain.usecase.create.CreateBeerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateBeerFactory {

    @Bean
    public CreateBeerUseCase factoryCreateBeerUseCase(BeerRepository beerRepository) {
        return CreateBeerUseCase.builder()
                .beerRepository(beerRepository)
                .build();
    }
}
