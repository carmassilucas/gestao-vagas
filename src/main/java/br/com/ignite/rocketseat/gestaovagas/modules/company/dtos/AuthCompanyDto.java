package br.com.ignite.rocketseat.gestaovagas.modules.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class AuthCompanyDto {
    private String username;
    private String password;
}
