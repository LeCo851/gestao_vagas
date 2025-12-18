package br.com.leandrocoelho.gestaovagas.modules.candidate.repository;

import br.com.leandrocoelho.gestaovagas.modules.candidate.entities.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {

}
