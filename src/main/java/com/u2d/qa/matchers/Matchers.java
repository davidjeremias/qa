package com.u2d.qa.matchers;

import java.util.Calendar;

public class Matchers {

    public static DiaSemanaMatcher caiEm(Integer diaSemana) {
        return new DiaSemanaMatcher(diaSemana);
    }

    public static DiaSemanaMatcher caiNaSegunda() {
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }
}
