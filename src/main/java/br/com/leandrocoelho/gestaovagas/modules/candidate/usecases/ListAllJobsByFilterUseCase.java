package br.com.leandrocoelho.gestaovagas.modules.candidate.usecases;

import br.com.leandrocoelho.gestaovagas.modules.company.entities.JobEntity;
import br.com.leandrocoelho.gestaovagas.modules.company.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListAllJobsByFilterUseCase {

    private final JobRepository jobRepository;

    public List<JobEntity> execute(String filter){
       return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
