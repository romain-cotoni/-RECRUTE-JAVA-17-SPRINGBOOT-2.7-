package fr.projet.app.security;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.projet.app.model.Role;
import fr.projet.app.model.Utilisateur;
import fr.projet.app.repository.UtilisateurRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService
{
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
		
		Set<Role> roles = utilisateur.getRoles();
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		roles.forEach(role -> { authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRolename())); });
		
		UserDetails userDetails = (UserDetails)new User(utilisateur.getUsername(), utilisateur.getPassword(), authorities);
		return userDetails;
	}

}
