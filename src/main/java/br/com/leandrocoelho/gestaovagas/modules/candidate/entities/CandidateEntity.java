package br.com.leandrocoelho.gestaovagas.modules.candidate.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Schema(example = "Daniel de souza", requiredMode = Schema.RequiredMode.REQUIRED, description = "nome do candidato")
    private String name;

    @NotBlank
    @Pattern(regexp = "^\\S*$", message = "O campo [username] não deve conter espaços em branco" )
    @Schema(example = "daniel", requiredMode = Schema.RequiredMode.REQUIRED, description = "username do candidato")
    private String username;

    @Email(message = "O campo email deve conter um email válido")
    @Schema(example = "daniel@gmail.com", requiredMode = Schema.RequiredMode.REQUIRED, description = "email do candidato")
    private String email;

    @Length(min = 10, max=100, message = "O campo deve conter entre 10 e 100 caracteres")
    @Schema(example = "Admin@1234",maxLength = 100, minLength = 10, requiredMode = Schema.RequiredMode.REQUIRED, description = "senha do candidato")
    private String password;
    @Schema(example = "Desenvolvedor java", requiredMode = Schema.RequiredMode.REQUIRED, description = "breve descrição do candidato")
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
