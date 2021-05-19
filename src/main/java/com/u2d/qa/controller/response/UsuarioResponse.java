package com.u2d.qa.controller.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioResponse {

    @EqualsAndHashCode.Include
    private Long id;
    private String nome;
}
