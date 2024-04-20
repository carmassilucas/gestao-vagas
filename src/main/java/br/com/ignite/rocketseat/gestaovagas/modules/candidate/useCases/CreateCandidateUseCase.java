package br.com.ignite.rocketseat.gestaovagas.modules.candidate.useCases;

import br.com.ignite.rocketseat.gestaovagas.exceptions.UserFoundException;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.CandidateEntity;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.candidateRepository.save(candidate);
    }

}
