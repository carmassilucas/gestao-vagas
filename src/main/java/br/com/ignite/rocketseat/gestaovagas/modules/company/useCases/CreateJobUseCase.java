package br.com.ignite.rocketseat.gestaovagas.modules.company.useCases;

import br.com.ignite.rocketseat.gestaovagas.modules.company.entities.JobEntity;
import br.com.ignite.rocketseat.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }
}
