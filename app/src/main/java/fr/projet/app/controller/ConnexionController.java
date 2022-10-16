package fr.projet.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.projet.app.model.JwtRequest;
import fr.projet.app.model.JwtResponse;
import fr.projet.app.model.Utilisateur;

import fr.projet.app.security.JwtTokenUtil;
import fr.projet.app.security.JwtUserDetailsService;
import fr.projet.app.service.UtilisateurService;


@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", maxAge=3600)
@RestController
@RequestMapping("/auth")
public class ConnexionController 
{
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authRequest) throws Exception 
    {	
    	// If credential is valid, it uses the JwtTokenUtil class to generate a new access token, which is then attached to the response object of type AuthResponse.
    	try
    	{
			authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())); //Authentication authentication =//
    		UserDetails utilisateur       = jwtUserDetailsService.loadUserByUsername(authRequest.getUsername());
    		String token                  = jwtTokenUtil.generateToken(utilisateur);
    		JwtResponse response          = new JwtResponse(utilisateur.getUsername(), token);
    		return ResponseEntity.ok().body(response);
    	}
    	catch(BadCredentialsException exception)
    	{
    		System.out.println("bad credentials exception");
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();// If credential is invalid, BradCredentialsException is thrown and the API returns HTTP status 401 (Unauthorized)
    	}
    }
    
    
    @PostMapping("/createUser")
    public ResponseEntity<?> createUtilisateur(@RequestBody Utilisateur utilisateur) throws Exception
    {	
    	utilisateurService.createUtilisateur(utilisateur);
    	return ResponseEntity.ok().body(utilisateur);
    }
    
    
    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUtilisateur(@RequestBody Utilisateur utilisateur) throws Exception 
    {	
    	utilisateurService.updateUtilisateur(utilisateur);
    	return ResponseEntity.ok().body(utilisateur);
    }
    
    
    @PostMapping("/deleteUser")
    public ResponseEntity<?> deleteUtilisateur(@RequestBody int idUtilisateur) throws Exception 
    {
    	utilisateurService.deleteUtilisateur(idUtilisateur);
    	return ResponseEntity.ok().body(idUtilisateur);
    }
}
