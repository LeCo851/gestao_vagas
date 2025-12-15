package br.com.leandrocoelho.gestaovagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {

    @Schema(example = "vaga para pessoa dev junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "VR/VA", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "Junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
