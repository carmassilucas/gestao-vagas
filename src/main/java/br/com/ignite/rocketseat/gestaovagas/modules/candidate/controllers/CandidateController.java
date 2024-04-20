package br.com.ignite.rocketseat.gestaovagas.modules.candidate.controllers;

import br.com.ignite.rocketseat.gestaovagas.exceptions.UserFoundException;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.CandidateEntity;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.CandidateRepository;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
