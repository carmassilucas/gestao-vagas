package br.com.ignite.rocketseat.gestaovagas.modules.candidate.useCases;

import br.com.ignite.rocketseat.gestaovagas.exceptions.UserNotFoundException;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.CandidateRepository;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.dtos.ProfileCandidateResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDto execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(UserNotFoundException::new);

        return ProfileCandidateResponseDto.builder()
                .id(candidate.getId())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .description(candidate.getDescription())
                .build();
    }
}
