package br.com.leandrocoelho.gestaovagas.modules.company.controllers;

import br.com.leandrocoelho.gestaovagas.modules.company.dto.CreateJobDTO;
import br.com.leandrocoelho.gestaovagas.modules.company.entities.JobEntity;
import br.com.leandrocoelho.gestaovagas.modules.company.usecases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    private final CreateJobUseCase createJobUseCase;

    public JobController(CreateJobUseCase createJobUseCase){
        this.createJobUseCase=createJobUseCase;
    }

    @PostMapping("/")
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
