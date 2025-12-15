package br.com.leandrocoelho.gestaovagas.modules.candidate.controller;


import br.com.leandrocoelho.gestaovagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.leandrocoelho.gestaovagas.modules.candidate.usecases.AuthCandidateUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    private final AuthCandidateUseCase authCandidateUseCase;

    public AuthCandidateController(AuthCandidateUseCase authCandidateUseCase){
        this.authCandidateUseCase = authCandidateUseCase;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO){

        try {
            var token = this.authCandidateUseCase.execute(authCandidateRequestDTO);
            return  ResponseEntity.ok().body(token);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }
}
