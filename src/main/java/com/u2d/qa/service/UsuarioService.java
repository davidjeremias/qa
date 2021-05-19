package com.u2d.qa.service;

import com.u2d.qa.controller.request.UsuarioRequest;
import com.u2d.qa.controller.response.UsuarioResponse;
import com.u2d.qa.entity.Usuario;
import com.u2d.qa.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    ModelMapper mapper;

    public Optional<UsuarioResponse> save(UsuarioRequest request) {
        Usuario usuario = repository.save(mapper.map(request, Usuario.class));
        return Optional.of(mapper.map(usuario, UsuarioResponse.class));
    }
}
