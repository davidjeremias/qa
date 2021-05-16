package com.u2d.qa.service;

import org.springframework.stereotype.Service;

@Service
public class CalculadoraService {

    public int soma(int num1, int num2) {
        return num1 + num2;
    }

    public int subtracao(int num1, int num2) {
        return num1 - num2;
    }

    public int divisao(int num1, int num2) {
        return num1 / num2;
    }
}
