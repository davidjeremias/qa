package com.u2d.qa.controller;

import com.u2d.qa.controller.request.UsuarioRequest;
import com.u2d.qa.controller.response.UsuarioResponse;
import com.u2d.qa.entity.Usuario;
import com.u2d.qa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> save(@Valid @RequestBody UsuarioRequest request) {
        Optional<UsuarioResponse> response = usuarioService.save(request);
        return new ResponseEntity<UsuarioResponse>(response.get(), HttpStatus.CREATED);
    }
}
