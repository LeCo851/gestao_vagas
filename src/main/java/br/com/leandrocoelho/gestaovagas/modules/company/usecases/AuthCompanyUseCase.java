package br.com.leandrocoelho.gestaovagas.modules.company.usecases;

import br.com.leandrocoelho.gestaovagas.modules.company.dto.AuthCompanyDTO;
import br.com.leandrocoelho.gestaovagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder){
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String execute(AuthCompanyDTO authCompanyDTO)  {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("Company/password incorrect")

        );

        // verificar se senha é igual
       var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

       if(!passwordMatches){
          throw new BadCredentialsException("Company/password incorrect");
       }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return  JWT.create().withIssuer("javagas") // retorna o token
                .withSubject(company.getId().toString())
                .sign(algorithm);

    }
}
