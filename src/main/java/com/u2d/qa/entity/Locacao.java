package com.u2d.qa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locacao")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLocacao")
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="idLocacao")
    private List<Filme> filmes;

    @Column(name = "dataLocacao")
    private Date dataLocacao;

    @Column(name = "dataRetorno")
    private Date dataRetorno;

    @Column(name = "valor")
    private Double valor;
}
