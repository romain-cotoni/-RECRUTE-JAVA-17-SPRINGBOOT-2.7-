package fr.projet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="ville")
public class Ville
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_vil")
    private int IdVille;

    @Column(name="ville_vil", length=50)
    private String ville;

    @Column(name="postal_vil", length=50)
    private String postal;

    @OneToMany(mappedBy = "ville", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Candidat> candidats = new HashSet<Candidat>();

    @OneToMany(mappedBy = "ville", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Entreprise> entreprises = new HashSet<Entreprise>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pys", nullable = true)
    private Pays pays;

    public Ville()
    {

    }

    public Ville(String ville, String postal, Set<Candidat> candidats, Set<Entreprise> entreprises, Pays pays) {
        this.ville = ville;
        this.postal = postal;
        this.candidats = candidats;
        this.entreprises = entreprises;
        this.pays = pays;
    }

    public int getIdVille() {
        return IdVille;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public Set<Candidat> getCandidats() {
        return candidats;
    }

    public void setCandidats(Set<Candidat> candidats) {
        this.candidats = candidats;
    }

    public Set<Entreprise> getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(Set<Entreprise> entreprises) {
        this.entreprises = entreprises;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville1 = (Ville) o;
        return Objects.equals(ville, ville1.ville) && Objects.equals(postal, ville1.postal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ville, postal);
    }
}
