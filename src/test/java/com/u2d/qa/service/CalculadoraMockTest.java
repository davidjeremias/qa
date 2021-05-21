package com.u2d.qa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculadoraMockTest {

    @Mock
    private CalculadoraService calculadoraService;

    @Test
    public void calcTest() {
        //cenario
        when(calculadoraService.soma(anyInt(), anyInt())).thenReturn(5);

        //ação
        int result = calculadoraService.soma(2, 3);

        //verificacao
        Assertions.assertEquals(result, 5);
    }
}
