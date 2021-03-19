package com.beerhouse.domain.repository;

import com.beerhouse.domain.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeerRepository extends JpaRepository<Beer, String> {

    Optional<Beer> findById(long id);
}
