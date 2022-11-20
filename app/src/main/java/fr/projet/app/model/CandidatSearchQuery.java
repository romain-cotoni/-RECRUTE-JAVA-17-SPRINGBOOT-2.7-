package fr.projet.app.model;

/*import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor*/
public class CandidatSearchQuery
{
    private String prenom;
    private String nom;
    private String telephone;
    private String email;
    private Boolean teletravail;
    private Boolean handicape;
    private Boolean disponible;
    private String diplomes;
    private String specialites;
    private String missions;
    private String entreprises;
    private String competences;
    private String langues;
    private String pseudos;
    private String ville;
    private Integer mobilite;

    public CandidatSearchQuery()
    {

    }

    public CandidatSearchQuery(String prenom, String nom, String telephone, String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getTeletravail() {
        return teletravail;
    }

    public void setTeletravail(Boolean teletravail) {
        this.teletravail = teletravail;
    }

    public Boolean getHandicape() {
        return handicape;
    }

    public void setHandicape(Boolean handicape) {
        this.handicape = handicape;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public String getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(String diplomes) {
        this.diplomes = diplomes;
    }

    public String getSpecialites() {
        return specialites;
    }

    public void setSpecialites(String specialites) {
        this.specialites = specialites;
    }

    public String getMissions() {
        return missions;
    }

    public void setMissions(String missions) {
        this.missions = missions;
    }

    public String getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(String entreprises) {
        this.entreprises = entreprises;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public String getLangues() {
        return langues;
    }

    public void setLangues(String langues) {
        this.langues = langues;
    }

    public String getPseudos() {
        return pseudos;
    }

    public void setPseudos(String pseudos) {
        this.pseudos = pseudos;
    }

    public Integer getMobilite() {
        return mobilite;
    }

    public void setMobilite(Integer mobilite) {
        this.mobilite = mobilite;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
