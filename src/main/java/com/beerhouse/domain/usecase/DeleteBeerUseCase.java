package com.beerhouse.domain.usecase;

import com.beerhouse.domain.repository.BeerRepository;
import lombok.Builder;

@Builder
public class DeleteBeerUseCase {

    private BeerRepository beerRepository;

    public void execute(Long beerId) {

    }
}
