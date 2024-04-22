package br.com.ignite.rocketseat.gestaovagas.modules.candidate.dtos;

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
    private String username;
    private String email;
    private String name;
    private String description;
}
