package fr.projet.app.controller;

import fr.projet.app.model.Candidat;
import fr.projet.app.security.JwtTokenUtil;
import fr.projet.app.security.JwtUserDetailsService;
import fr.projet.app.service.CandidatService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CandidatController.class)
public class CandidatControllerTests
{
    @MockBean //add mock to spring application context
    private CandidatService candidatService;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private MockMvc mockMvc; //Encapsulates all web application beans and makes them available for testing

    @Test
    @WithMockUser(username = "test", password = "test", roles = "admin")
    public void testGetCandidats() throws Exception
    {
        Candidat candidat = new Candidat("Bart",
                                           "Simpson",
                                           "bart.simpson@mail.com",
                                           "06 52 84 45 07");
        List<Candidat> candidats = Arrays.asList(candidat);

        //Mockito.when(candidatService.findAllCandidats()).thenReturn(candidats);
        BDDMockito.given(candidatService.findAllCandidats()).willReturn(candidats);// BDDMockito.given similar to Mockito.when

        mockMvc.perform(get("/candidats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].prenom", Matchers.is("Bart")))
                .andExpect(jsonPath("$[0].nom", Matchers.is("Simpson")))
                .andExpect(jsonPath("$[0].email", Matchers.is("bart.simpson@mail.com")))
                .andExpect(jsonPath("$[0].mob", Matchers.is("06 52 84 45 07")));
    }
}

