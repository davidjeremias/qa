package com.u2d.qa.service;

import com.u2d.qa.entity.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    public void notificarAtraso(Usuario usuario) {
        log.info("Usuário: {} informado", usuario.getNome());
    }

}
