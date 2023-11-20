package br.com.ignite.rocketseat.gestaovagas.modules.candidates.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public @Data class AuthCandidateResponseDto {
    private String access_token;
    private Long expires_in;
}
