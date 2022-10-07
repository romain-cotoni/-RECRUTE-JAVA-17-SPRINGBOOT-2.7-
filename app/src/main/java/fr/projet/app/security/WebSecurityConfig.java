package fr.projet.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true, jsr250Enabled = true)
public class WebSecurityConfig 
{     
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
    
	
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
    
    @Autowired 
    JwtUserDetailsService jwtUserDetailsService;

     
    public UserDetailsService userDetailsService() 
    {
        return new JwtUserDetailsService();
    }
    
    
    @Bean
    BCryptPasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
     
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception 
    {
        return authConfig.getAuthenticationManager();
    }
    
    
    @Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception
    {
    	httpSecurity.csrf().disable()
		.authorizeRequests()
		.antMatchers("/auth/login").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.anyRequest().authenticated()
		.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
    	// Add a filter to validate the tokens with every request
    	httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    	
    	return httpSecurity.build();
    }
}
