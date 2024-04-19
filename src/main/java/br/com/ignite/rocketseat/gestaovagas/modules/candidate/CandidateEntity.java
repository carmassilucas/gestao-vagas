package br.com.ignite.rocketseat.gestaovagas.modules.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CandidateEntity {
    private UUID id;
    private String name;

    @Pattern(regexp = "\\S+", message = "Nome de usuário não deve conter espaços")
    private String username;

    @Email(message = "Endereço de e-mail inválido")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
    private String password;

    private String description;
    private String curriculum;
}
