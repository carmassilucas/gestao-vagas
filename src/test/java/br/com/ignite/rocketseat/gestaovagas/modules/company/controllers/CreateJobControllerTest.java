package br.com.ignite.rocketseat.gestaovagas.modules.company.controllers;

import br.com.ignite.rocketseat.gestaovagas.modules.company.dtos.CreateJobDto;
import br.com.ignite.rocketseat.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.ignite.rocketseat.gestaovagas.modules.company.repositories.CompanyRepository;
import br.com.ignite.rocketseat.gestaovagas.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CreateJobControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private CompanyRepository companyRepository;

    @Value("security.token.secret")
    private String secret;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("Should be able to create a new job")
    void should_be_able_to_create_a_new_job() throws Exception {
        var company = CompanyEntity.builder()
                .email("email@company.com")
                .username("COMPANY_USERNAME")
                .password("1234567890")
                .name("COMPANY_NAME")
                .description("COMPANY_DESCRIPTION")
                .build();

        company = this.companyRepository.save(company);

        var createdJobDto = CreateJobDto.builder()
                .description("DESCRIPTION_TEST")
                .benefits("BENEFITS_TEST")
                .level("LEVEL_TEST")
                .build();

        this.mvc.perform(
                MockMvcRequestBuilders.post("/company/job")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJSON(createdJobDto))
                        .header("Authorization", TestUtils.generateToken(
                                company.getId(),
                                secret
                        ))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Should not_be_able_to_create_a_new_job_if_company_not_found")
    void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception {
        var createdJobDto = CreateJobDto.builder()
                .description("DESCRIPTION_TEST")
                .benefits("BENEFITS_TEST")
                .level("LEVEL_TEST")
                .build();

        this.mvc.perform(
                MockMvcRequestBuilders.post("/company/job")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJSON(createdJobDto))
                        .header("Authorization", TestUtils.generateToken(
                                UUID.randomUUID(),
                                secret
                        ))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
