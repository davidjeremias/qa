package com.u2d.qa.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Filme {

    private String nome;
    private Integer estoque;
    private Double precoLocacao;
}
