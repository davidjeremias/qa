package com.u2d.qa.service;

import com.u2d.qa.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public class SPCService {

    public boolean possuiNegativacao(Usuario usuario) {
        return usuario.getNegativado();
    }
}
