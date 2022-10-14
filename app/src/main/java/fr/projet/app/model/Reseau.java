package fr.projet.app.model;

import javax.persistence.*;

@Entity
@Table(name = "reseau")
public class Reseau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_res", nullable = false)
    private Integer idReseau;

    @Column(name="reseau_res", length=50)
    private String reseau;

    public Reseau()
    {
        
    }

    public Integer getIdReseau() {
        return idReseau;
    }


}