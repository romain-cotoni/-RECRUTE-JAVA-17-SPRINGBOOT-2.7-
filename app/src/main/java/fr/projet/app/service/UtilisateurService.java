package fr.projet.app.service;

import fr.projet.app.model.Role;
import fr.projet.app.model.Utilisateur;
import fr.projet.app.repository.RoleRepository;
import fr.projet.app.repository.UtilisateurRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UtilisateurService 
{
	private UtilisateurRepository utilisateurRepository; 
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bcryptEncoder;

	public UtilisateurService(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository, BCryptPasswordEncoder bcryptEncoder)
	{
		this.utilisateurRepository = utilisateurRepository;
		this.roleRepository = roleRepository;
		this.bcryptEncoder = bcryptEncoder;
	}

	
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