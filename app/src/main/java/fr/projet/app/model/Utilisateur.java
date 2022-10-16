package fr.projet.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "utilisateur")
public class Utilisateur 
{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_utl")
	private int idUtilisateur;
	
	@Column(name="user_utl", unique=true, nullable=false, length=50)
	@NotNull
	@Length(min = 4, max = 50)
	private String username;
	
	@Column(name="pass_utl", nullable = false, length=250)
	private String password;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "utilisateur_role", 
	           joinColumns        = { @JoinColumn(name = "id_utl", referencedColumnName = "id_utl") }, 
	           inverseJoinColumns = { @JoinColumn(name = "id_rle", referencedColumnName = "id_rle") })
	private Set<Role> roles = new HashSet<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() 
	{
		return idUtilisateur;
	}

	public Set<Role> getRoles()
	{
		return roles;
	}
	
	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}
	
	public void addRole(Role role)
	{
        this.roles.add(role);
    }
	
	
	public String toString() 
	{
		return "Utilisateur [id=" + idUtilisateur + ", username=" + username + ", password=" + password + "]";
	}
}
