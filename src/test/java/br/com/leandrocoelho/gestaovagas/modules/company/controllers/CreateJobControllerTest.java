package br.com.leandrocoelho.gestaovagas.modules.company.controllers;

import br.com.leandrocoelho.gestaovagas.exceptions.CompanyNotFoundException;
import br.com.leandrocoelho.gestaovagas.modules.company.dto.CreateJobDTO;
import br.com.leandrocoelho.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.leandrocoelho.gestaovagas.modules.company.repositories.CompanyRepository;
import br.com.leandrocoelho.gestaovagas.modules.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;



@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateJobControllerTest {

    private  MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    void should_be_able_to_create_new_job() throws Exception {

        var company = CompanyEntity.builder()
                .description("company_description")
                .email("email@company.com")
                .password("1234567890")
                .username("company_username")
                .name("company_name")
                .build();

        company = companyRepository.saveAndFlush(company);

        var createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJSON(createJobDTO))
                        .header("Authorization",TestUtils.generateToken(company.getId(),"JAVAGAS_123@#"))
                        ).andExpect(MockMvcResultMatchers.status().isOk());

    }
    @Test
    void should_not_be_able_to_create_new_job_if_company_not_found() throws Exception {

        var createJobDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJSON(createJobDTO))
                .header("Authorization",TestUtils.generateToken(UUID.randomUUID(),"JAVAGAS_123@#"))
                // tenho que capturar o erro no exception handler controller para que o assert funcione
        ).andExpect(result -> assertInstanceOf(CompanyNotFoundException.class, result.getResolvedException()));
    }

}
