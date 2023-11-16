package br.com.ignite.rocketseat.gestaovagas.modules.company.entities;

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

@Entity(name = "company")
public @Data class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Pattern(regexp = "\\S+", message = "não deve conter espaços")
    private String username;

    @Email(message = "deve ser um endereço de e-mail com formato correto")
    private String email;

    @Length(min = 10, max = 100, message = "o tamanho deve estar entre 10 e 100")
    private String password;

    private String name;
    private String description;
    private String website;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
