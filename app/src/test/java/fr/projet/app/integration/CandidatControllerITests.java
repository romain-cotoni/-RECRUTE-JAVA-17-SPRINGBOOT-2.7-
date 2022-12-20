package fr.projet.app.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.projet.app.controller.CandidatController;
import fr.projet.app.model.Candidat;
import fr.projet.app.repository.CandidatRepository;
import fr.projet.app.security.JwtTokenUtil;
import fr.projet.app.security.JwtUserDetailsService;
import fr.projet.app.service.CandidatService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CandidatControllerITests
{
    private Candidat candidat;
    private Candidat candidatSaved;
    private List<Candidat> candidats = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup()
    {
        Candidat candidat = new Candidat("test","test","test@mail.com","test");
        candidats.add(candidat);
        candidatSaved = candidatRepository.save(candidat);
    }

    @AfterEach
    public void wrapup()
    {
        candidatRepository.delete(candidatSaved);
    }

    @Test
    @WithMockUser(username = "test", password = "test", roles = "admin")
    public void givenCandidatObject_whenCreateCandidat_thenReturnSavedCandidat() throws Exception
    {
        //when - action or behaviour to test
        ResultActions response = mockMvc.perform(get("/candidats"))
                                        .andExpect(status().isOk())
                                        .andDo(print());

        /*ResultActions response = mockMvc.perform(post("/candidat")
                .param("prenom", "test")
                .param("nom", "test")
                .param("email", "test@mail.com")
                .param("mob", "test")
        );
            //.contentType(MediaType.APPLICATION_JSON)
            //.content(objectMapper.writeValueAsString(candidat)));
         */

        //then - verify the result or output using assert statements
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prenom", is(candidat.getPrenom())));

        /*List<Candidat> candidats = candidatController.getCandidats();
        Assertions.assertThat(candidats).first().hasFieldOrPropertyWithValue("prenom", "bart");

        candidatController.delete(candidatResult.getId());
        Assertions.assertThat(candidatController.read()).isEmpty();*/
    }
}
