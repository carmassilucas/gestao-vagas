package br.com.ignite.rocketseat.gestaovagas.modules.company.dtos;

import lombok.Data;

public @Data class CreateJobDto {
    private String description;
    private String benefits;
    private String level;
}
