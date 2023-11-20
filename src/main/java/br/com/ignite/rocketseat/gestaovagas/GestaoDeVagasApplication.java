package br.com.ignite.rocketseat.gestaovagas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Gestão de vagas",
				description = "Api responsável pela gestão de vagas",
				version = "1"
		)
)
public class GestaoDeVagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoDeVagasApplication.class, args);
	}

}
