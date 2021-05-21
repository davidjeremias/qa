package com.u2d.qa.builders;

import com.u2d.qa.entity.Usuario;

public class UsuarioBuilder {

    private Usuario usuario;

    private UsuarioBuilder() {
    }

    public static UsuarioBuilder umUsuario() {
        UsuarioBuilder builder = new UsuarioBuilder();
        builder.usuario = new Usuario();
        builder.usuario.setNome("David");
        builder.usuario.setNegativado(false);
        return builder;
    }

    public UsuarioBuilder comNome(String nome) {
        usuario.setNome(nome);
        return this;
    }

    public UsuarioBuilder comId(Long id) {
        usuario.setId(id);
        return this;
    }

    public Usuario agora() {
        return usuario;
    }
}
