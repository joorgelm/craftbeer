package com.beerhouse.domain.usecase.create;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.helper.Validator;
import com.beerhouse.domain.repository.BeerRepository;
import lombok.Builder;

import java.util.Objects;

@Builder
public class CreateBeerUseCase {

    private BeerRepository beerRepository;

    public CreateBeerUseCaseOutput execute(CreateBeerUseCaseInput createBeerInput) {

        validate(createBeerInput);

        Beer createdBeer = createBeer(createBeerInput);

        createdBeer = beerRepository.save(createdBeer);

        return toOutput(createdBeer);
    }

    private static Beer createBeer(CreateBeerUseCaseInput createBeerInput) {
        return Beer.builder()
                .name(createBeerInput.getName())
                .category(createBeerInput.getCategory())
                .alcoholContent(createBeerInput.getAlcoholContent())
                .ingredients(createBeerInput.getIngredients())
                .price(createBeerInput.getPrice())
                .build();
    }

    private static CreateBeerUseCaseOutput toOutput(Beer beer) {
        return CreateBeerUseCaseOutput.builder()
                .id(beer.getId())
                .name(beer.getName())
                .category(beer.getCategory())
                .alcoholContent(beer.getAlcoholContent())
                .ingredients(beer.getIngredients())
                .price(beer.getPrice())
                .build();
    }

    private static void validate(CreateBeerUseCaseInput createBeerInput) {
        Validator.of(createBeerInput)
                .validate(CreateBeerUseCaseInput::getName, Objects::nonNull, "Nome não informado")
                .validate(CreateBeerUseCaseInput::getAlcoholContent, Objects::nonNull, "Teor alcoolico não informado")
                .validate(CreateBeerUseCaseInput::getCategory, Objects::nonNull, "Categoria não informado")
                .validate(CreateBeerUseCaseInput::getIngredients, Objects::nonNull, "Ingredientes não informado")
                .validate(CreateBeerUseCaseInput::getPrice, Objects::nonNull, "Preco não informado");
    }
}
