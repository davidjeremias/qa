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
        return builder;
    }

    public Usuario agora() {
        return usuario;
    }
}
