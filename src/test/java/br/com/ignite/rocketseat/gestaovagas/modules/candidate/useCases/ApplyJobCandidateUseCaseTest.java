package br.com.ignite.rocketseat.gestaovagas.modules.candidate.useCases;

import br.com.ignite.rocketseat.gestaovagas.exceptions.JobNotFoundException;
import br.com.ignite.rocketseat.gestaovagas.exceptions.UserNotFoundException;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.CandidateEntity;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.CandidateRepository;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.entity.ApplyJobEntity;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.repository.ApplyJobRepository;
import br.com.ignite.rocketseat.gestaovagas.modules.company.entities.JobEntity;
import br.com.ignite.rocketseat.gestaovagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    void should_not_be_able_to_apply_job_with_candidate_not_found() {
        assertThrows(UserNotFoundException.class,
                () -> applyJobCandidateUseCase.execute(null, null));
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    void should_not_be_able_to_apply_job_with_job_not_found() {
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        assertThrows(JobNotFoundException.class,
                () -> applyJobCandidateUseCase.execute(idCandidate, null));
    }

    @Test
    void should_be_able_to_create_a_new_apply_job() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        var job = JobEntity.builder()
                .id(idJob)
                .build();

        when(jobRepository.findById(idJob)).thenReturn(Optional.of(job));

        var applyJob = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        when(applyJobRepository.save(applyJob)).thenReturn(applyJob);

        assertEquals(applyJobCandidateUseCase.execute(idCandidate, idJob), applyJob);
    }

}
