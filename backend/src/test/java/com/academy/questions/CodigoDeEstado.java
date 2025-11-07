package com.academy.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CodigoDeEstado implements Question<Integer> {

    public static Question<Integer> obtenido() {
        return new CodigoDeEstado();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return SerenityRest.lastResponse().statusCode();
    }
}