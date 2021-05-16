package com.u2d.qa.service;

import com.u2d.qa.entity.Usuario;
import liquibase.pro.packaged.U;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertTest {

    @Test
    public void test() {
        Assertions.assertTrue(true);
        Assertions.assertFalse(false);

        Assertions.assertEquals(1, 1);

        //Delta é uma margem de erro na comparação para numeros com ponto flutuante
        Assertions.assertEquals(0.51, 0.51, 0.01);
        Assertions.assertEquals(Math.PI, 3.14, 0.01);

        //Wrapper
        int i = 5;
        Integer i1 = 5;
        Assertions.assertEquals(i, i1);

        Assertions.assertEquals("bola", "bola");
        Assertions.assertNotEquals("bola", "Bola");
        Assertions.assertTrue("bola".equalsIgnoreCase("Bola"));

        //Objetos
        Usuario u1 = new Usuario("David");
        Usuario u2 = new Usuario("David");
        Usuario u3 = null;
        Assertions.assertEquals(u1, u2);
        Assertions.assertNotNull(u2);
        Assertions.assertNull(u3);

        //verifica se as instancias são iguais
        //Assertions.assertSame(u1, u2);
        Assertions.assertNotSame(u1, u2);
    }
}
