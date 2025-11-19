package br.com.leandrocoelho.gestao_vagas.modules.candidate.controller;


import br.com.leandrocoelho.gestao_vagas.modules.candidate.CandidateEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateController (CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity){
        return this.candidateRepository.save(candidateEntity);
    }
}
