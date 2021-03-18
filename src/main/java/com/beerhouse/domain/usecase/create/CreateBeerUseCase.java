package com.beerhouse.domain.usecase.create;

import com.beerhouse.domain.repository.BeerRepository;
import lombok.Builder;

@Builder
public class CreateBeerUseCase {

    private BeerRepository beerRepository;

    public CreateBeerUseCaseOutput execute(CreateBeerUseCaseInput createBeerInput) {
        return null;
    }
}
