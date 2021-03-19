package com.beerhouse.domain.helper;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Validator<T> {
    private final T t;

    private Validator(T t) {
        this.t = t;
    }

    public static <T> Validator<T> of(T t) {
        return new Validator<>(Objects.requireNonNull(t));
    }

    public Validator<T> validate(Predicate<T> validation, String message) {
        if (!validation.test(t)) {
            throw new IllegalStateException(message);
        }
        return this;
    }

    public <U> Validator<T> validate(Function<T, U> projection, Predicate<U> validation, String message) {
        return validate(projection.andThen(validation::test)::apply, message);
    }
}
