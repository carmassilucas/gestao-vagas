package br.com.ignite.rocketseat.gestaovagas.modules.candidates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public @Data class ProfileCandidateResponseDto {
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String description;
}
