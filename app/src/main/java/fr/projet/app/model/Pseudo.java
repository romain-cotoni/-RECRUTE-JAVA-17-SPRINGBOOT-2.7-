package fr.projet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "pseudo")
public class Pseudo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_psd", nullable = false)
    private Integer idPseudo;

    @Column(name="pseudo_psd", length=500)
    private String pseudo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cdt")
    @JsonIgnore
    private Candidat candidat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_res")
    private Reseau reseau;

    public Pseudo()
    {

    }

    public Integer getIdPseudo() {
        return idPseudo;
    }



}