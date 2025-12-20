package br.com.leandrocoelho.gestaovagas.modules.company.usecases;

import br.com.leandrocoelho.gestaovagas.exceptions.CompanyNotFoundException;
import br.com.leandrocoelho.gestaovagas.modules.company.entities.JobEntity;
import br.com.leandrocoelho.gestaovagas.modules.company.repositories.CompanyRepository;
import br.com.leandrocoelho.gestaovagas.modules.company.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateJobUseCase {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;



    public JobEntity execute(JobEntity jobEntity){

        companyRepository.findById(jobEntity.getCompanyID()).orElseThrow(CompanyNotFoundException::new);

        return this.jobRepository.save(jobEntity);

    }
}

