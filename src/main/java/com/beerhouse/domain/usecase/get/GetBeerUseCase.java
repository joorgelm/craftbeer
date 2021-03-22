package com.beerhouse.domain.usecase.get;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.repository.BeerRepository;
import lombok.Builder;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Builder
public class GetBeerUseCase {

    private BeerRepository beerRepository;

    public GetBeerUseCaseOutput get(Long beerId) {

        if (Objects.nonNull(beerId)) {
            Beer beer = getBeer(beerId);
            return generateOutput(beer);
        }


        List<Beer> beers = beerRepository.findAll();
        return generateOutputList(beers);
    }

    private GetBeerUseCaseOutput generateOutput(Beer beer) {
        return GetBeerUseCaseOutputSingle.builder()
                .beer(beer)
                .build();
    }

    private GetBeerUseCaseOutputList generateOutputList(List<Beer> beers) {
        return GetBeerUseCaseOutputList.builder()
                .beers(beers)
                .build();
    }

    private Beer getBeer(long beerId) {
        return beerRepository.findById(beerId)
                .orElseThrow(() -> new EntityNotFoundException("Cerveja não encontrada"));
    }
}
