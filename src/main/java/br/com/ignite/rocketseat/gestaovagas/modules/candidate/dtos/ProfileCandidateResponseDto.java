package br.com.ignite.rocketseat.gestaovagas.modules.candidate.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDto {
    private UUID id;

    @Schema(example = "maria")
    private String username;

    @Schema(example = "maria@rocketseat.com.br")
    private String email;

    @Schema(example = "Maria de Souza")
    private String name;

    @Schema(example = "Desenvolvedora Java Pleno")
    private String description;
}
