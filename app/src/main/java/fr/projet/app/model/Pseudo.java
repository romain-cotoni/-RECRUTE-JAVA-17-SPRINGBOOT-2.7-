package fr.projet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pseudo")
public class Pseudo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_psd", nullable = false)
    private Integer idPseudo;

    @Column(name="pseudo_psd", length=50)
    private String pseudo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cdt")
    @JsonIgnore
    private Candidat candidat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_res")
    private Reseau reseau;

    public Pseudo()
    {

    }

    public Pseudo(String pseudo, Candidat candidat, Reseau reseau) {
        this.pseudo = pseudo;
        this.candidat = candidat;
        this.reseau = reseau;
    }

    public Integer getIdPseudo() {
        return idPseudo;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Reseau getReseau() {
        return reseau;
    }

    public void setReseau(Reseau reseau) {
        this.reseau = reseau;
    }

}