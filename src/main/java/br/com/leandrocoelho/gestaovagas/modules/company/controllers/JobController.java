package br.com.leandrocoelho.gestaovagas.modules.company.controllers;

import br.com.leandrocoelho.gestaovagas.modules.company.dto.CreateJobDTO;
import br.com.leandrocoelho.gestaovagas.modules.company.entities.JobEntity;
import br.com.leandrocoelho.gestaovagas.modules.company.usecases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
@PreAuthorize("hasRole('COMPANY')")
public class JobController {

    private final CreateJobUseCase createJobUseCase;

    public JobController(CreateJobUseCase createJobUseCase){
        this.createJobUseCase=createJobUseCase;
    }

    @PostMapping("/")
    @Tag(name = "Vagas", description = "Informações das vagas")
    @Operation(summary = "Cadastro de vagas", description = "Função responsável por cadastrar as vagas na empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200" ,content = {
                    @Content(schema = @Schema(implementation = JobEntity.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO , HttpServletRequest request){
        var companyID = request.getAttribute("company_id");

        var jobEntity =
            JobEntity.builder()
                    .benefits(createJobDTO.getBenefits())
                    .companyID(UUID.fromString(companyID.toString()))
                    .description(createJobDTO.getBenefits())
                    .level(createJobDTO.getLevel())
                    .build();

            return this.createJobUseCase.execute(jobEntity);
    }
}
