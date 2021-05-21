package com.u2d.qa.service;

import com.u2d.qa.entity.Filme;
import com.u2d.qa.entity.Locacao;
import com.u2d.qa.entity.Usuario;
import com.u2d.qa.exception.FilmeSemEstoqueException;
import com.u2d.qa.exception.LocadoraException;
import com.u2d.qa.repository.LocacaoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CalculoValorLocacaoTest {

    @InjectMocks
    private LocacaoService locacaoService;

    @Mock
    private LocacaoRepository repository;

    @Mock
    private SPCService spcService;

    private static Usuario usuario;

    @BeforeAll
    public static void setup() {
        usuario = new Usuario(1L,"David", false);
    }

    private static Filme filme1 = new Filme(1L,"MPV1", 2, 4.0);
    private static Filme filme2 = new Filme(2L,"MPV2", 2, 4.0);
    private static Filme filme3 = new Filme(3L,"MPV3", 2,4.0);
    private static Filme filme4 = new Filme(4L,"MPV4", 2,4.0);
    private static Filme filme5 = new Filme(5L,"MPV5", 2,4.0);
    private static Filme filme6 = new Filme(6L,"MPV6", 2,4.0);

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
