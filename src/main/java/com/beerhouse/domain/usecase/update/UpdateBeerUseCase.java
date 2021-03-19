package com.beerhouse.domain.usecase.update;

import com.beerhouse.domain.entity.Beer;
import com.beerhouse.domain.helper.Validator;
import com.beerhouse.domain.repository.BeerRepository;
import lombok.Builder;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@Builder
public class UpdateBeerUseCase {
    private BeerRepository beerRepository;

    public void update(UpdateBeerUseCaseInput input) {
        Beer beerToUpdate = getBeer(input);

        update(input, beerToUpdate);

        beerRepository.save(beerToUpdate);
    }

    private static void update(UpdateBeerUseCaseInput input, Beer beerToUpdate) {
        Optional.ofNullable(input.getName()).ifPresent(beerToUpdate::setName);
        Optional.ofNullable(input.getPrice()).ifPresent(beerToUpdate::setPrice);
        Optional.ofNullable(input.getAlcoholContent()).ifPresent(beerToUpdate::setAlcoholContent);
        Optional.ofNullable(input.getCategory()).ifPresent(beerToUpdate::setAlcoholContent);
        Optional.ofNullable(input.getIngredients()).ifPresent(beerToUpdate::setIngredients);
    }

    private Beer getBeer(UpdateBeerUseCaseInput input) {
        return beerRepository.findById(input.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cerveja não encontrada"));
    }

    //todo: talvez separar uma classe
    public void updateAll(UpdateBeerUseCaseInput input) {
        validate(input);

        Beer beerToUpdate = getBeer(input);

        update(input, beerToUpdate);

        beerRepository.save(beerToUpdate);
    }

    private static void validate(UpdateBeerUseCaseInput input) {
        Validator.of(input)
                .validate(UpdateBeerUseCaseInput::getName, Objects::nonNull, "Nome não informado")
                .validate(UpdateBeerUseCaseInput::getAlcoholContent, Objects::nonNull, "Teor alcoolico não informado")
                .validate(UpdateBeerUseCaseInput::getCategory, Objects::nonNull, "Categoria não informado")
                .validate(UpdateBeerUseCaseInput::getIngredients, Objects::nonNull, "Ingredientes não informado")
                .validate(UpdateBeerUseCaseInput::getPrice, Objects::nonNull, "Preco não informado");
    }
}
