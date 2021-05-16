package com.u2d.qa.service;

import com.u2d.qa.entity.Filme;
import com.u2d.qa.entity.Locacao;
import com.u2d.qa.entity.Usuario;
import com.u2d.qa.exception.FilmeSemEstoqueException;
import com.u2d.qa.exception.LocadoraException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculoValorLocacaoTest {

    private static LocacaoService locacaoService;
    private static Usuario usuario;

    @BeforeAll
    public static void setup() {
        locacaoService = new LocacaoService();
        usuario = new Usuario("David");
    }

    private static Filme filme1 = new Filme("MPV1", 2, 4.0);
    private static Filme filme2 = new Filme("MPV2", 2, 4.0);
    private static Filme filme3 = new Filme("MPV3", 2,4.0);
    private static Filme filme4 = new Filme("MPV4", 2,4.0);
    private static Filme filme5 = new Filme("MPV5", 2,4.0);
    private static Filme filme6 = new Filme("MPV6", 2,4.0);

    private static Stream<Object> addFixture() {
        return Stream.of(
                Arguments.of(Arrays.asList(filme1, filme2, filme3), 11.0D),
                Arguments.of(Arrays.asList(filme1, filme2, filme3, filme4), 13.0D),
                Arguments.of(Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0D),
                Arguments.of(Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0D)
        );
    }

    @ParameterizedTest
    @MethodSource("addFixture")
    public void deveCalcularValorLocacaoConsiderandoDescontos(List<Filme> filmes, Double valorLocacao) throws LocadoraException, FilmeSemEstoqueException {
        //ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        //validação
        assertEquals(valorLocacao, locacao.getValor(), 10);
    }
}
