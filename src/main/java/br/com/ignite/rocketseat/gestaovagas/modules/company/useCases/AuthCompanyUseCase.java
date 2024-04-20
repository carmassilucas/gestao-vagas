package br.com.ignite.rocketseat.gestaovagas.modules.company.useCases;

import br.com.ignite.rocketseat.gestaovagas.modules.company.dtos.AuthCompanyDto;
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

@Service
public class AuthCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("security.token.secret")
    private String secretKey;

    public String execute(AuthCompanyDto authCompanyDto) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha incorreto"));

        var passwordMatches = this.passwordEncoder.matches(authCompanyDto.getPassword(), company.getPassword());

        if (!passwordMatches)
            throw new AuthenticationException();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create().withIssuer("Ignite - Rocketseat")
                .withSubject(company.getId().toString())
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .sign(algorithm);
    }
}
