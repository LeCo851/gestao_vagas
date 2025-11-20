package  br.com.leandrocoelho.gestaovagas.modules.company.usecases;

import br.com.leandrocoelho.gestaovagas.exceptions.UserFoundException;
import br.com.leandrocoelho.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.leandrocoelho.gestaovagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;

    @Autowired // autowired opcional neste caso, pois a classe só possui um constutor
    public CreateCompanyUseCase (CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public CompanyEntity execute(CompanyEntity companyEntity){
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(),companyEntity.getEmail())
                .ifPresent(user ->{
                    throw new UserFoundException();
                });

        return this.companyRepository.save(companyEntity);
    }
}
