package br.com.ignite.rocketseat.gestaovagas.modules.candidate.useCases;

import br.com.ignite.rocketseat.gestaovagas.exceptions.JobNotFoundException;
import br.com.ignite.rocketseat.gestaovagas.exceptions.UserNotFoundException;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.CandidateRepository;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.entity.ApplyJobEntity;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.repository.ApplyJobRepository;
import br.com.ignite.rocketseat.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(UserNotFoundException::new);

        this.jobRepository.findById(idJob)
                .orElseThrow(JobNotFoundException::new);

        return applyJobRepository.save(
                ApplyJobEntity.builder()
                        .candidateId(idCandidate)
                        .jobId(idJob)
                        .build()
        );
    }
}
