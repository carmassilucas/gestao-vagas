package br.com.ignite.rocketseat.gestaovagas.modules.candidates.useCases;

import br.com.ignite.rocketseat.gestaovagas.modules.candidates.CandidateRepository;
import br.com.ignite.rocketseat.gestaovagas.modules.candidates.dto.AuthCandidateRequestDto;
import br.com.ignite.rocketseat.gestaovagas.modules.candidates.dto.AuthCandidateResponseDto;
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
    @Value("${security.token.secret.candidate}")
    private String secretKey;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDto execute(AuthCandidateRequestDto authCandidateRequestDto) throws AuthenticationException {
        var candidate = candidateRepository.findByUsername(authCandidateRequestDto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        var passwordMatches = this.passwordEncoder.matches(
                authCandidateRequestDto.password(),
                candidate.getPassword()
        );

        if (!passwordMatches)
            throw new AuthenticationException();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create()
                .withIssuer(candidate.getName())
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
