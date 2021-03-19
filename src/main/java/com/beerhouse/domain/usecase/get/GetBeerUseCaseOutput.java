package com.beerhouse.domain.usecase.get;

import com.beerhouse.domain.entity.Beer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetBeerUseCaseOutput {

    private List<Beer> beers;
}
