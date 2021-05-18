package com.u2d.qa.builders;

import com.u2d.qa.entity.Filme;

public class FilmeBuilder {

    private Filme filme;

    private FilmeBuilder() {}

    public static FilmeBuilder umFilme() {
        FilmeBuilder builder = new FilmeBuilder();
        builder.filme = new Filme();
        builder.filme.setEstoque(2);
        builder.filme.setNome("De volta para o futuro");
        builder.filme.setPrecoLocacao(4.0);
        return builder;
    }

    public FilmeBuilder comValor(Double valor) {
        filme.setPrecoLocacao(valor);
        return this;
    }

    public FilmeBuilder semEstoque() {
        filme.setEstoque(0);
        return this;
    }

    public Filme agora() {
        return filme;
    }
}
