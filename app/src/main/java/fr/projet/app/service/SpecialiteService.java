package fr.projet.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.projet.app.model.Specialite;
import fr.projet.app.repository.SpecialiteRepository;

@Service
public class SpecialiteService
{
	@Autowired
	SpecialiteRepository specialiteRepository;
	
	
	
	@Transactional
	public Specialite createSpecialite(Specialite specialite)
	{
		Optional<Specialite> optionalSpl = specialiteRepository.findAll().stream().filter(s -> s.equals(specialite)).findFirst();		
		if(optionalSpl.isPresent())
		{
			Specialite existSpl = optionalSpl.get();
			if(existSpl != null) return existSpl;
			else return specialiteRepository.save(specialite);
		}
		else
		{
			return specialiteRepository.save(specialite);
		}
	}
	
	
	
	@Transactional
	public Specialite updateSpecialite(Specialite specialite)
	{
		Optional<Specialite> optionalSpl = specialiteRepository.findAll().stream().filter(s -> s.equals(specialite)).findFirst();
		if(optionalSpl.isPresent())
		{
			Specialite existSpl = optionalSpl.get();
			if(existSpl != null) return existSpl;
			else return specialiteRepository.save(specialite);
		}
		else
		{
			return specialiteRepository.save(specialite);
		}
	}

	

	public void deleteSpecialite(int idSpecialite)
	{
		specialiteRepository.deleteById(idSpecialite);
		System.out.println("idSpl deleted : "+idSpecialite);
	}
	
	public Optional<Specialite> findSpecialiteById(int idSpecialite)
	{
		return specialiteRepository.findById(idSpecialite);
	}
	
}
	

