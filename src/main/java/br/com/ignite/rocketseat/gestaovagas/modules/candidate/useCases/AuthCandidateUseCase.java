package br.com.ignite.rocketseat.gestaovagas.modules.candidate.useCases;

import br.com.ignite.rocketseat.gestaovagas.modules.candidate.CandidateRepository;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.dtos.AuthCandidateRequestDto;
import br.com.ignite.rocketseat.gestaovagas.modules.candidate.dtos.AuthCandidateResponseDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("security.token.secret.candidate")
    private String secretKey;

    public AuthCandidateResponseDto execute(AuthCandidateRequestDto authCandidateRequestDto) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha incorreto"));

        var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDto.password(), candidate.getPassword());

        if (!passwordMatches)
            throw new AuthenticationException();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create()
                .withIssuer("Ignite - Rocketseat")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", List.of("CANDIDATE"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return AuthCandidateResponseDto.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();
    }
}
