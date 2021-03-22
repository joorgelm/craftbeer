package com.beerhouse.domain.usecase.get;

import com.beerhouse.domain.entity.Beer;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetBeerUseCaseOutputSingle implements GetBeerUseCaseOutput {

    private Beer beer;
}
