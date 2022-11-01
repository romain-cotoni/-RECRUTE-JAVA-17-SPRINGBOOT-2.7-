package fr.projet.app.repository;

import fr.projet.app.model.Candidat;
import fr.projet.app.model.Competence;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CandidatRepositoryTests
{
    private Candidat candidat;
    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    CompetenceRepository competenceRepository;

    @BeforeEach
    public void setup()
    {
        candidat = new Candidat("alice","doe");
    }

    //@DisplayName("JUNIT test for findByEmail method in CandidatRepository")
    @Test
    public void givenCandidatEmail_whenFindByEmail_thenReturnCandidatObject()
    {
        //given - precondition or setup
        //call to setup()

        //when - action or the behaviour to test
        Candidat savedCandidat = candidatRepository.save(candidat);

        //then - verify the output
        Assertions.assertThat(savedCandidat).isNotNull();
        Assertions.assertThat(savedCandidat.getIdCandidat()).isGreaterThan(0);
        Assertions.assertThat(savedCandidat.getPrenom()).isEqualTo("alice");
    }

    @Test
    public void testFindCompetencesByCandidatId()
    {
        int idCandidat = 1; //Candidat 'bart simpson' dans la base de données.

        Set<Competence> competencesResultat = candidatRepository.findCompetencesByCandidatId(idCandidat);
        Assertions.assertThat(competencesResultat).extracting(Competence::getNom).contains("java");
        Assertions.assertThat(competencesResultat).extracting(Competence::getNiveau).contains(1);
        Assertions.assertThat(competencesResultat).extracting(Competence::getType).contains(1);
        Assertions.assertThat(competencesResultat).extracting(Competence::getInfo).contains("Développer en java, un language de programmation objet");
    }

    @Test
    public void testCreateAndDelete()
    {
        Competence competence = new Competence("scala",4,1,"Développer en scala, un language de programmation multiparadigme");

        competenceRepository.save(competence);
        List<Competence> competencesResultat = competenceRepository.findAll();
        Assertions.assertThat(competencesResultat).extracting(Competence::getNom).containsOnlyOnce("scala");

        competenceRepository.delete(competence);
        competencesResultat = competenceRepository.findAll();
        Assertions.assertThat(competencesResultat).extracting(Competence::getNom).doesNotContain("scala");
    }
}
