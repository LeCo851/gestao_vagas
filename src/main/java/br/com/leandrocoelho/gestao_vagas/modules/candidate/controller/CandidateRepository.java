package br.com.leandrocoelho.gestao_vagas.modules.candidate.controller;

import br.com.leandrocoelho.gestao_vagas.modules.candidate.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {


}
