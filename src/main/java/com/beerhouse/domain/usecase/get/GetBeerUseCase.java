package com.beerhouse.domain.usecase.get;

import com.beerhouse.domain.repository.BeerRepository;
import lombok.Builder;

@Builder
public class GetBeerUseCase {

    private BeerRepository beerRepository;
}
