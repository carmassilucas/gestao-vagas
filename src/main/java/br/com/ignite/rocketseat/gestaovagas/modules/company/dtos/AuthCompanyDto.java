package br.com.ignite.rocketseat.gestaovagas.modules.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDto {
    private String username;
    private String password;
}
