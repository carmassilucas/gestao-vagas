package br.com.ignite.rocketseat.gestaovagas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Gestão de vagas",
				description = "APIRestful responsável pela gestão de vagas",
				version = "1.0.0"
		)
)
@SpringBootApplication
public class GestaoDeVagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoDeVagasApplication.class, args);
	}

}
