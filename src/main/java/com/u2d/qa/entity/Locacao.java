package com.u2d.qa.entity;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Locacao {

    private Usuario usuario;
    private List<Filme> filmes;
    private Date dataLocacao;
    private Date dataRetorno;
    private Double valor;
}
