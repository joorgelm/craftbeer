package com.beerhouse.domain.usecase.update;

import com.beerhouse.domain.repository.BeerRepository;
import lombok.Builder;

@Builder
public class UpdateBeerUseCase {
    private BeerRepository beerRepository;

    public void update(UpdateBeerUseCaseInput input) {}

    public void updateAll(UpdateBeerUseCaseInput input) {}
}
