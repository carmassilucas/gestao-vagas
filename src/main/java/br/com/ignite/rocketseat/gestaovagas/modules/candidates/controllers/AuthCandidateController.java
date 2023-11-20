package br.com.ignite.rocketseat.gestaovagas.modules.candidates.controllers;

import br.com.ignite.rocketseat.gestaovagas.modules.candidates.dto.AuthCandidateRequestDto;
import br.com.ignite.rocketseat.gestaovagas.modules.candidates.useCases.AuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDto authCandidateRequestDto) {
        try {
            var token = authCandidateUseCase.execute(authCandidateRequestDto);
            return ResponseEntity.ok().body(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
