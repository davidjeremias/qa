package com.u2d.qa.repository;

import com.u2d.qa.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

@Eager
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
