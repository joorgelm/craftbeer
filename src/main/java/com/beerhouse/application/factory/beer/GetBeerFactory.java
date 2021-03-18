package com.beerhouse.application.factory.beer;

import com.beerhouse.domain.repository.BeerRepository;
import com.beerhouse.domain.usecase.get.GetBeerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetBeerFactory {

    @Bean
    public GetBeerUseCase factoryGetBeerUseCase(BeerRepository beerRepository) {
        return GetBeerUseCase.builder()
                .beerRepository(beerRepository)
                .build();
    }
}
