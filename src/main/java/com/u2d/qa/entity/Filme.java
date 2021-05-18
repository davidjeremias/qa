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
@Table(name = "filme")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFilme")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "estoque")
    private Integer estoque;

    @Column(name = "precoLocacao")
    private Double precoLocacao;
}
