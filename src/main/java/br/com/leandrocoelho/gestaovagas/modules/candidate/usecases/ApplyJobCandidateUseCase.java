package br.com.leandrocoelho.gestaovagas.modules.candidate.usecases;

import br.com.leandrocoelho.gestaovagas.exceptions.JobNotFoundException;
import br.com.leandrocoelho.gestaovagas.exceptions.UserNotFoundException;
import br.com.leandrocoelho.gestaovagas.modules.candidate.entities.ApplyJobEntity;
import br.com.leandrocoelho.gestaovagas.modules.candidate.repository.ApplyJobRepository;
import br.com.leandrocoelho.gestaovagas.modules.candidate.repository.CandidateRepository;
import br.com.leandrocoelho.gestaovagas.modules.company.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplyJobCandidateUseCase {

    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
    private final ApplyJobRepository applyJobRepository;


    public ApplyJobEntity execute(UUID idCandidate, UUID idJob){
        //validar se o candidato existe
        this.candidateRepository.findById(idCandidate)
                .orElseThrow(UserNotFoundException::new);

        //validar se a vaga existe
        this.jobRepository.findById(idJob)
                .orElseThrow(JobNotFoundException::new);

        //candidato se inscrever na vaga
        var applyJob = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob).build();

        return applyJobRepository.save(applyJob);
    }
}
