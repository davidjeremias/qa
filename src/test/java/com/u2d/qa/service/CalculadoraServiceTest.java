package com.u2d.qa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalculadoraServiceTest {

    private static CalculadoraService calculadoraService;

    @BeforeAll
    public static void setup() {
        calculadoraService = new CalculadoraService();
    }

    @Test
    public void deveSomarDoisValores() {
        //cenario
        int num1 = 5;
        int num2 = 3;

        //ação
        int result = calculadoraService.soma(num1, num2);

        //validação
        Assertions.assertEquals(8, result);
    }

    @Test
    public void deveSubtrairDoisValores() {
        //cenario
        int num1 = 8;
        int num2 = 5;

        //ação
        int result = calculadoraService.subtracao(num1, num2);

        //validação
        Assertions.assertEquals(3, result);
    }

    @Test
    public void deveDividirDoisNumeros() {
        //cenario
        int num1 = 6;
        int num2 = 3;

        //ação
        int result = calculadoraService.divisao(num1, num2);

        //validação
        Assertions.assertEquals(2, result);
    }

    @Test
    public void deveLancarExceptionDividirPorZero() {
        //cenario
        int num1 = 6;
        int num2 = 0;

        //ação
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            calculadoraService.divisao(num1, num2);
        });

        //validação
        Assertions.assertEquals("/ by zero", exception.getMessage());
    }
}
