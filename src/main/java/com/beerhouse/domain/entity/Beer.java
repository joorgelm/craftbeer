package com.beerhouse.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "tb_beer")
public class Beer {
    /*
    * O uso do prefixo beer, pode ajudar caso exista
    * a necessidade de construir uma query complexa com vários relacionamentos,
    * nao e o caso mas nao vejo motivos para nao usar
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beer_id")
    private long id;

    @Column(name = "beer_name")
    private String name;

    @Column(name = "beer_ingredients")
    private String ingredients;

    @Column(name = "beer_alcoholContent")
    private String alcoholContent;

    @Column(name = "beer_price")
    private BigDecimal price;

    // todo: transformar em um enum ou criar um endpoint para categorias
    @Column(name = "beer_category")
    private String category;
}
