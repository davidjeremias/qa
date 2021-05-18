package com.u2d.qa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome")
    private String nome;
}
