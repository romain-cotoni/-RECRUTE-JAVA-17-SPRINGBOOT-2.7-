package fr.projet.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.projet.app.model.Role;
import fr.projet.app.model.Utilisateur;
import fr.projet.app.repository.RoleRepository;
import fr.projet.app.repository.UtilisateurRepository;


@Service
public class UtilisateurService 
{
	@Autowired
	private UtilisateurRepository utilisateurRepository; 
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	
	public Utilisateur findUtilisateurById(int id)
	{
		return utilisateurRepository.findById(id);
	}
	
	
	public List<Utilisateur> findAllUtilisateurs()
	{
		List<Utilisateur> utilisateur = utilisateurRepository.findAll();
		return utilisateur;
	}

	
	public Utilisateur createUtilisateur(Utilisateur utilisateur) 
	{
		utilisateur.setPassword(bcryptEncoder.encode(utilisateur.getPassword()));
		return utilisateurRepository.save(utilisateur);
	}


	public Utilisateur addRoleToUtilisateur(int idUtilisateur, int idRole)
	{   
		try
		{
			Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur);
			Role role               = roleRepository.findById(idRole);
		    utilisateur.addRole(role);
		    return utilisateurRepository.save(utilisateur);
		}
		catch(Exception exception)
		{
			System.out.println(exception);
			return null;
		}
	}
	
	
	public Utilisateur createUtilisateurWithRole(Utilisateur utilisateur, int idRole)
	{  
		try
		{
			Role role = roleRepository.findById(idRole);
			utilisateur.addRole(role);
		    return utilisateurRepository.save(utilisateur);		    
		}
		catch(Exception exception)
		{
			System.out.println(exception);
		}
		return null;
	}
	
	
	public int updateUtilisateur(Utilisateur utilisateur)
	{
		utilisateur.setPassword(bcryptEncoder.encode(utilisateur.getPassword()));
		return utilisateurRepository.updateUtilisateurById(utilisateur.getUsername(), utilisateur.getPassword(), utilisateur.getId());
	}
	
	
	public void deleteUtilisateur(int idUtilisateur)
	{
		utilisateurRepository.deleteById(idUtilisateur);
	}
}