package com.u2d.qa.service;

import com.u2d.qa.builders.LocacaoBuilder;
import com.u2d.qa.builders.UsuarioBuilder;
import com.u2d.qa.entity.Filme;
import com.u2d.qa.entity.Locacao;
import com.u2d.qa.entity.Usuario;
import com.u2d.qa.exception.FilmeSemEstoqueException;
import com.u2d.qa.exception.LocadoraException;
import com.u2d.qa.matchers.Matchers;
import com.u2d.qa.repository.LocacaoRepository;
import com.u2d.qa.util.DataUtils;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.u2d.qa.builders.LocacaoBuilder.*;
import static com.u2d.qa.builders.UsuarioBuilder.*;
import static com.u2d.qa.util.DataUtils.isMesmaData;
import static com.u2d.qa.util.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocacaoServiceTest {

    @InjectMocks
    private LocacaoService locacaoService;

    @Mock
    private LocacaoRepository repository;

    @Mock
    private SPCService spcService;

    @Mock
    private EmailService emailService;

    private static Usuario usuario;
    private static List<Filme> filmes;

    @BeforeAll
    public static void setup() {
        usuario = umUsuario().agora();
        filmes = Arrays.asList(Filme.builder()
                .nome("De volta para o futuro")
                .estoque(2)
                .precoLocacao(15.99).build());
    }

    @Test
    public void deveAlugarFilme() throws Exception {
        Assumptions.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //acão
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        //verificação
        assertThat(locacao.getValor(), is(15.99));
        assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }

    @Test
    public void deveLancarExcecaoAoAlugarFilmeSemEstoque() throws FilmeSemEstoqueException {
        //cenário
        List<Filme> filmes1 = Arrays.asList(Filme.builder()
                .nome("De volta para o futuro")
                .estoque(0)
                .precoLocacao(15.99).build());

        //ação
        FilmeSemEstoqueException exception = assertThrows(FilmeSemEstoqueException.class, () -> {
            locacaoService.alugarFilme(usuario, filmes1);
        });

        //verificação
        assertEquals("Filme sem estoque", exception.getMessage());
    }

    @Test
    public void deveLancarExcecaoAoAlugarFilmeSemUsuario() throws LocadoraException {
        //ação
        LocadoraException exception = assertThrows(LocadoraException.class, () -> {
            locacaoService.alugarFilme(null, filmes);
        });

        //verificação
        assertEquals("Usuário vazio", exception.getMessage());
    }

    @Test
    public void deveLancarExcecaoAoAlugarFilmeSemFilme() throws LocadoraException {
        //cenario
        List<Filme> filmesVazios = new ArrayList<>();

        //ação
        LocadoraException exception = assertThrows(LocadoraException.class, () -> {
            locacaoService.alugarFilme(usuario, filmesVazios);
        });

        //verificação
        assertEquals("Filme vazio", exception.getMessage());
    }

    @Test
    public void deveDevolverNaSegundaAoAlugarNoSabado() throws LocadoraException, FilmeSemEstoqueException {
        Assumptions.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //ação
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        //verificação
        assertThat(locacao.getDataRetorno(), Matchers.caiNaSegunda());
    }

    @Test
    public void naoDeveAlugarFilmeParaNegativado() throws LocadoraException {
        //cenario
        when(spcService.possuiNegativacao(usuario)).thenReturn(true);

        //ação
        LocadoraException exception = assertThrows(LocadoraException.class, () -> {
            locacaoService.alugarFilme(usuario, filmes);
        });

        //verificação
        assertEquals("Usuário Negativado", exception.getMessage());
        verify(spcService).possuiNegativacao(usuario);
    }

    @Test
    public void deveEnviarEmailParaLocacoesAtrasadas() {
        //cenario
        Usuario usuario = umUsuario().agora();
        Usuario usuario2 = umUsuario().comNome("fe").agora();
        List<Locacao> locacoes = Arrays.asList(
                umaLocacao().atrasada().comUsuario(usuario).agora(),
                umaLocacao().comUsuario(usuario2).agora()
        );
        when(repository.obterLocacoesPendentes()).thenReturn(locacoes);

        //ação
        locacaoService.notificarAtrasos();

        //verificacao
        verify(emailService).notificarAtraso(usuario);
        //verify(emailService, never()).notificarAtraso(usuario2);
    }
}
