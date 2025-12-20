package br.com.leandrocoelho.gestaovagas.modules.company.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "company")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Pattern(regexp = "^\\S*$", message = "O campo [username] não deve conter espaços em branco" )
    private String username;

    @Email(message = "O campo email deve conter um email válido")
    private String email;

    @Length(min = 10, max=100, message = "O campo deve conter entre 10 e 100 caracteres")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // melhores práticas para que a senha não vaze em json ou possa ser usado em metodos toString
    @ToString.Exclude
    private String password;

    private String website;
    private String name;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
