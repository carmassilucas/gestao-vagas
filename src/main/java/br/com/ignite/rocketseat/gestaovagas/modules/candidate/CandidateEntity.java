package br.com.ignite.rocketseat.gestaovagas.modules.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Daniel de Souza", requiredMode = Schema.RequiredMode.REQUIRED, description = "Nome do candidato")
    private String name;

    @Pattern(regexp = "\\S+", message = "Nome de usuário não deve conter espaços")
    @Schema(example = "daniel", requiredMode = Schema.RequiredMode.REQUIRED, description = "Username do candidato")
    private String username;

    @Email(message = "Endereço de e-mail inválido")
    @Schema(example = "daniel@rocketseat.com.br", requiredMode = Schema.RequiredMode.REQUIRED, description = "E-mail do candidato")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
    @Schema(example = "daniel@12345", minLength = 10, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED, description = "Senha do candidato")
    private String password;

    @Schema(example = "Desenvolvedor Java Júnior", requiredMode = Schema.RequiredMode.REQUIRED, description = "Breve descrição do candidato")
    private String description;

    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
