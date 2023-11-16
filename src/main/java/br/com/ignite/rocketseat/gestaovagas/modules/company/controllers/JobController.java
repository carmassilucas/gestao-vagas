package br.com.ignite.rocketseat.gestaovagas.modules.company.controllers;

import br.com.ignite.rocketseat.gestaovagas.modules.company.dtos.CreateJobDto;
import br.com.ignite.rocketseat.gestaovagas.modules.company.entities.JobEntity;
import br.com.ignite.rocketseat.gestaovagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;


    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody CreateJobDto createJobDto, HttpServletRequest httpServletRequest) {
        var companyId = httpServletRequest.getAttribute("company_id");

        var jobEntity = JobEntity.builder()
                .benefits(createJobDto.getBenefits())
                .description(createJobDto.getDescription())
                .level(createJobDto.getLevel())
                .companyId(UUID.fromString(companyId.toString()))
                .build();

        return this.createJobUseCase.execute(jobEntity);
    }
}
