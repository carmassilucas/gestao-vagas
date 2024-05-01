package br.com.ignite.rocketseat.gestaovagas.modules.candidate.controllers;

import br.com.ignite.rocketseat.gestaovagas.modules.candidate.dtos.AuthCandidateRequestDto;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.useCases.AuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {
    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDto authCandidateRequestDto) {
        try {
            var token = this.authCandidateUseCase.execute(authCandidateRequestDto);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
