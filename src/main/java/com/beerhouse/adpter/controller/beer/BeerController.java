package com.beerhouse.adpter.controller.beer;

import com.beerhouse.domain.usecase.create.CreateBeerUseCaseInput;
import com.beerhouse.domain.usecase.create.CreateBeerUseCaseOutput;
import com.beerhouse.domain.usecase.update.UpdateBeerUseCaseInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "beers")
public interface BeerController {

    @PostMapping
    @ResponseBody
    ResponseEntity<CreateBeerUseCaseOutput> create (
            @RequestBody CreateBeerUseCaseInput createBeerInput
    );

    @PatchMapping("/{beerId}")
    ResponseEntity<Void> update (
            @PathVariable("beerId") Long beerId,
            @RequestBody UpdateBeerUseCaseInput updateBeerInput
    ) throws IllegalAccessException;

    @PutMapping("/{beerId}")
    ResponseEntity<Void> updateAll (
            @PathVariable("beerId") Long beerId,
            @RequestBody UpdateBeerUseCaseInput updateBeerInput
    );

    @DeleteMapping("/{beerId}")
    ResponseEntity<Void> delete (
            @PathVariable("beerId") Long beerId
    );
}
