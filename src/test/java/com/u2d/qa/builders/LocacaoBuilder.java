package com.u2d.qa.builders;

import com.u2d.qa.entity.Filme;
import com.u2d.qa.entity.Locacao;
import com.u2d.qa.entity.Usuario;

import java.util.Arrays;
import java.util.Date;

import static com.u2d.qa.builders.FilmeBuilder.*;
import static com.u2d.qa.builders.UsuarioBuilder.*;
import static com.u2d.qa.util.DataUtils.*;

public class LocacaoBuilder {

    private Locacao locacao;

    private LocacaoBuilder(){}

    public static LocacaoBuilder umaLocacao() {
        LocacaoBuilder builder = new LocacaoBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    private static void inicializarDadosPadroes(LocacaoBuilder builder) {
        builder.locacao = new Locacao();
        Locacao locacao = builder.locacao;

        locacao.setUsuario(umUsuario().agora());
        locacao.setFilmes(Arrays.asList(umFilme().agora()));
        locacao.setDataLocacao(new Date());
        locacao.setDataRetorno(obterDataComDiferencaDias(1));
        locacao.setValor(4.0);
    }

    public LocacaoBuilder comUsuario(Usuario usuario){
        locacao.setUsuario(usuario);
        return this;
    }

    public LocacaoBuilder comListaFilmes(Filme... filme) {
        locacao.setFilmes(Arrays.asList(filme));
        return this;
    }

    public LocacaoBuilder comDataLocacao(Date date) {
        locacao.setDataLocacao(date);
        return this;
    }

    public LocacaoBuilder comDataRetorno(Date date) {
        locacao.setDataRetorno(date);
        return this;
    }

    public LocacaoBuilder atrasada() {
        locacao.setDataLocacao(obterDataComDiferencaDias(-4));
        locacao.setDataRetorno(obterDataComDiferencaDias(-2));
        return this;
    }

    public Locacao agora() {
        return locacao;
    }
}
