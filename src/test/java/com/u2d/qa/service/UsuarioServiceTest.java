package com.u2d.qa.service;

import com.u2d.qa.controller.request.UsuarioRequest;
import com.u2d.qa.controller.response.UsuarioResponse;
import com.u2d.qa.entity.Usuario;
import com.u2d.qa.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.convention.NamingConventions;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private static ModelMapper mapper;

    @Test
    public void save() {
        //cenario
        UsuarioResponse usuarioResponse = UsuarioResponse.builder().id(1L).nome("David").build();
        UsuarioRequest usuarioRequest = UsuarioRequest.builder().nome("David").build();
        Usuario usuario = Usuario.builder().id(1L).nome("David").build();
        when(mapper.map(usuarioRequest, Usuario.class)).thenReturn(usuario);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(mapper.map(usuario, UsuarioResponse.class)).thenReturn(usuarioResponse);

        //ação
        Optional<UsuarioResponse> response = usuarioService.save(usuarioRequest);

        //verificação
        assertNotNull(response.get());
    }
}
