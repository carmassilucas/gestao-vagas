package br.com.ignite.rocketseat.gestaovagas.modules.company.useCases;

import br.com.ignite.rocketseat.gestaovagas.exceptions.CompanyNotFoundException;
import br.com.ignite.rocketseat.gestaovagas.modules.company.entities.JobEntity;
import br.com.ignite.rocketseat.gestaovagas.modules.company.repositories.CompanyRepository;
import br.com.ignite.rocketseat.gestaovagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        this.companyRepository.findById(jobEntity.getCompanyId())
                .orElseThrow(CompanyNotFoundException::new);

        return this.jobRepository.save(jobEntity);
    }
}
