package br.com.ignite.rocketseat.gestaovagas.modules.candidates.useCases;

import br.com.ignite.rocketseat.gestaovagas.exceptions.CandidateFoundException;
import br.com.ignite.rocketseat.gestaovagas.modules.candidates.CandidateEntity;
import br.com.ignite.rocketseat.gestaovagas.modules.candidates.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(
                candidateEntity.getUsername(),
                candidateEntity.getEmail()
        ).ifPresent((user) -> {
            throw new CandidateFoundException();
        });

        return this.candidateRepository.save(candidateEntity);
    }
}
