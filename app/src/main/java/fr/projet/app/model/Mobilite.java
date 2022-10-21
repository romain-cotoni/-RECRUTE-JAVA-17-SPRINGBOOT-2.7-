package fr.projet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "mobilite")
public class Mobilite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mbl", nullable = false)
    private Integer idMobilite;

    @Column(name="zone_mbl", nullable = false)
    private Integer zone;

    @Column(name="rayon_mbl")
    private Integer rayon;

    @OneToMany(mappedBy = "mobilite", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Candidat> candidats = new HashSet<Candidat>();

    public Mobilite()
    {

    }



    public Integer getIdMobilite() {
        return idMobilite;
    }

    public Integer getZone() {
        return zone;
    }

    public void setZone(Integer zone) {
        this.zone = zone;
    }

    public Integer getRayon() {
        return rayon;
    }

    public void setRayon(Integer rayon) {
        this.rayon = rayon;
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
        Mobilite mobilite = (Mobilite) o;
        return Objects.equals(zone, mobilite.zone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zone);
    }
}