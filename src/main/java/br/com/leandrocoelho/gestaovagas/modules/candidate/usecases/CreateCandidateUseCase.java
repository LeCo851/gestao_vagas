package br.com.leandrocoelho.gestaovagas.modules.candidate.usecases;

import br.com.leandrocoelho.gestaovagas.exceptions.UserFoundException;
import br.com.leandrocoelho.gestaovagas.modules.candidate.entities.CandidateEntity;
import br.com.leandrocoelho.gestaovagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {


    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateCandidateUseCase (CandidateRepository candidateRepository, PasswordEncoder passwordEncoder) { // construtor da classe
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public CandidateEntity execute(CandidateEntity candidateEntity){
            this.candidateRepository
                    .findByUsernameOrEmail(candidateEntity.getUsername(),candidateEntity.getEmail())
                    .ifPresent(user -> {
                        throw new UserFoundException();
                    });

            var password = passwordEncoder.encode(candidateEntity.getPassword());
            candidateEntity.setPassword(password);

            return this.candidateRepository.save(candidateEntity);
        }
    }

