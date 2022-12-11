package fr.projet.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "role")
public class Role 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_rle")
	private int idRole;
	
	@Column(name="role_rle", nullable = false, length = 50)
	@NotNull
	@Length(min = 4, max = 50)
	//@Pattern(regexp = "^[A-Za-z'\\-_ ]*$")
	private String rolename;
	
	
	public String getRolename()
	{
		return rolename;
	}

	public void setRolename(String rolename) 
	{
		this.rolename = rolename;
	}

	public int getId() 
	{
		return idRole;
	}
}
