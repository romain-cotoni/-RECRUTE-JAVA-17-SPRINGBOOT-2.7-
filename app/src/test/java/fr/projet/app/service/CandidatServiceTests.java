package fr.projet.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import fr.projet.app.model.Candidat;
import fr.projet.app.model.CandidatSearchQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import fr.projet.app.repository.CandidatRepository;
import fr.projet.app.service.CandidatService;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CandidatServiceTests
{
    @Mock
    private CandidatRepository candidatRepository;
    @InjectMocks
    private CandidatService candidatService;

    @Test
    void testFindCandidatByParams()
    {
        CandidatSearchQuery candidatSearch = new CandidatSearchQuery("bart","simpson","06 52 84 45 07","bart.simpson@mail.com");

        List<Candidat> listCandidatsFound = new ArrayList<>();
        Candidat candidatFound = new Candidat("bart","simpson","06 52 84 45 07","bart.simpson@mail.com");
        listCandidatsFound.add(candidatFound);

        List<Candidat> listCandidatsExpected = new ArrayList<>();
        Candidat candidatExpected = new Candidat("bart", "simpson","06 52 84 45 07","bart.simpson@mail.com");
        listCandidatsExpected.add(candidatExpected);

        when(candidatRepository.findByParams(
        		candidatSearch.getPrenom(),
                candidatSearch.getNom(),
                candidatSearch.getTelephone(),
                candidatSearch.getEmail(),
                candidatSearch.getTeletravail(),
                candidatSearch.getHandicape(),
                candidatSearch.getDisponible(),
                candidatSearch.getDiplomes(),
                candidatSearch.getSpecialites(),
                candidatSearch.getMissions(),
                candidatSearch.getEntreprises(),
                candidatSearch.getCompetences(),
                candidatSearch.getLangues(),
                candidatSearch.getPseudos(),
                candidatSearch.getVille(),
                candidatSearch.getMobilite()))
                .thenReturn(listCandidatsFound);

        List<Candidat> listCandidatsResultat = candidatService.findCandidatsByParams(candidatSearch);
        assertEquals(1,listCandidatsResultat.size());
        assertThat(listCandidatsResultat.get(0)).usingRecursiveComparison().isEqualTo(listCandidatsExpected.get(0));
    }
}
