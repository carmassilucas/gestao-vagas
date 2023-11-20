package br.com.ignite.rocketseat.gestaovagas.modules.candidates.useCases;

import br.com.ignite.rocketseat.gestaovagas.modules.candidates.CandidateRepository;
import br.com.ignite.rocketseat.gestaovagas.modules.candidates.dto.ProfileCandidateResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDto execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return ProfileCandidateResponseDto.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .description(candidate.getDescription())
                .build();
    }

}
