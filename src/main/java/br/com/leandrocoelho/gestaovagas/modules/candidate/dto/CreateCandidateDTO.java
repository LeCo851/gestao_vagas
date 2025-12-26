package br.com.leandrocoelho.gestaovagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateCandidateDTO(
        @Schema(example = "Daniel de Souza", requiredMode = Schema.RequiredMode.REQUIRED)
        String name,
        @Schema(example = "daniel", requiredMode = Schema.RequiredMode.REQUIRED)
        String username,
        @Schema(example = "daniel@gmail.com",requiredMode = Schema.RequiredMode.REQUIRED)
        String email,
        @Schema(example = "1234567890", requiredMode = Schema.RequiredMode.REQUIRED)
        String password,
        @Schema(example = "descricao")
        String description,
        @Schema(example = "curriculo")
        String curriculum
) {
}
