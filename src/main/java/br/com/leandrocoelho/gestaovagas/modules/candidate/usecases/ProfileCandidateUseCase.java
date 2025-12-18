package br.com.leandrocoelho.gestaovagas.modules.candidate.usecases;

import br.com.leandrocoelho.gestaovagas.modules.candidate.repository.CandidateRepository;
import br.com.leandrocoelho.gestaovagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public ProfileCandidateUseCase(CandidateRepository candidateRepository){
        this.candidateRepository=candidateRepository;
    }

    public ProfileCandidateResponseDTO execute(UUID idCandidate){

        var candidate =  this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();
    }
}
