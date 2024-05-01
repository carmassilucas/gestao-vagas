package br.com.ignite.rocketseat.gestaovagas.modules.company.useCases;

import br.com.ignite.rocketseat.gestaovagas.modules.company.dtos.AuthCompanyDto;
import br.com.ignite.rocketseat.gestaovagas.modules.company.dtos.AuthCompanyResponseDto;
import br.com.ignite.rocketseat.gestaovagas.modules.company.repositories.CompanyRepository;
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
public class AuthCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("security.token.secret")
    private String secretKey;

    public AuthCompanyResponseDto execute(AuthCompanyDto authCompanyDto) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha incorreto"));

        var passwordMatches = this.passwordEncoder.matches(authCompanyDto.getPassword(), company.getPassword());

        if (!passwordMatches)
            throw new AuthenticationException();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token =  JWT.create().withIssuer("Ignite - Rocketseat")
                .withSubject(company.getId().toString())
                .withClaim("roles", List.of("COMPANY"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return AuthCompanyResponseDto.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();
    }
}
