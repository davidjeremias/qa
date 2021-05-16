package com.u2d.qa.entity;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @EqualsAndHashCode.Include
    private String nome;
}
