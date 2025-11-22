package br.com.leandrocoelho.gestaovagas.modules.candidate.usecases;

import br.com.leandrocoelho.gestaovagas.exceptions.UserFoundException;
import br.com.leandrocoelho.gestaovagas.modules.candidate.entities.CandidateEntity;
import br.com.leandrocoelho.gestaovagas.modules.candidate.controller.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {


    private final CandidateRepository candidateRepository;

    @Autowired
    public CreateCandidateUseCase (CandidateRepository candidateRepository) { // construtor da classe
        this.candidateRepository = candidateRepository;
    }


    public CandidateEntity execute(CandidateEntity candidateEntity){
            this.candidateRepository
                    .findByUsernameOrEmail(candidateEntity.getUsername(),candidateEntity.getEmail())
                    .ifPresent(user -> {
                        throw new UserFoundException();
                    });
            return this.candidateRepository.save(candidateEntity);
        }
    }

