package fr.projet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pays")
public class Pays
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pys", nullable = false)
    private Integer idPays;

    @Column(name = "pays_pys", length=50)
    @Length(min=2, max=50)
    private String pays;

    @Column(name = "national_pys", length=50)
    @Length(min=2, max=50)
    private String nationnalite;

    @OneToMany(mappedBy="pays",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Ville> villes = new HashSet<Ville>();

    @OneToMany(mappedBy="pays",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Candidat> candidats = new HashSet<Candidat>();


    public Pays()
    {

    }

    public Pays(String nationnalite)
    {
        this.nationnalite = nationnalite;
    }
    
    public Pays(String pays, String nationnalite, Set<Ville> villes, Set<Candidat> candidats)
    {
        this.pays = pays;
        this.nationnalite = nationnalite;
        this.villes = villes;
        this.candidats = candidats;
    }

    public Integer getIdPays() {
        return idPays;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getNationnalite() {
        return nationnalite;
    }

    public void setNationnalite(String nationnalite) {
        this.nationnalite = nationnalite;
    }

    public Set<Ville> getVilles() {
        return villes;
    }

    public void setVilles(Set<Ville> villes) {
        this.villes = villes;
    }

    public Set<Candidat> getCandidats() {
        return candidats;
    }

    public void setCandidats(Set<Candidat> candidats) {
        this.candidats = candidats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pays pays = (Pays) o;
        return Objects.equals(idPays, pays.idPays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPays);
    }
}