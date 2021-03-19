package com.beerhouse.domain.usecase.update;

import com.beerhouse.domain.usecase.BaseInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBeerUseCaseInput extends BaseInput {
    private Long id;
    private String name;
    private String ingredients;
    private String alcoholContent;
    private BigDecimal price;
    private String category;
}
