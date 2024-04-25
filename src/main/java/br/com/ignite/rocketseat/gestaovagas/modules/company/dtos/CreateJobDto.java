package br.com.ignite.rocketseat.gestaovagas.modules.company.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDto {
    @Schema(example = "Vaga para pessoa desenvolvedora Java Spring", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "Plano de saúde, Vale refeição e transporte", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "Júnior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
