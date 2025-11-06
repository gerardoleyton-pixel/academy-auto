package com.academy.question;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CodigoRespuesta implements Question<Integer> {

    public static CodigoRespuesta current() {
        return new CodigoRespuesta();
    }

    @Override
    public Integer answeredBy(Actor actor) {
        if (SerenityRest.lastResponse() == null) return -1;
        return SerenityRest.lastResponse().statusCode();
    }
}
