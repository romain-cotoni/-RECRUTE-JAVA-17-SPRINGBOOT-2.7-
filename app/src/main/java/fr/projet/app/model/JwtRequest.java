package fr.projet.app.model;

public class JwtRequest 
{
	private String username;

	private String password;

	private int roleId;

	
	public JwtRequest()
	{
		
	}
	
	
	public JwtRequest(String username, String password) 
	{
		this.username = username;
		this.password = password;
	}
	
	public JwtRequest(String username, String password, int roleId) 
	{
		this.username = username;
		this.password = password;
		this.roleId   = roleId;
	}


	public String getUsername() 
	{
		return username;
	}

	
	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	
	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
