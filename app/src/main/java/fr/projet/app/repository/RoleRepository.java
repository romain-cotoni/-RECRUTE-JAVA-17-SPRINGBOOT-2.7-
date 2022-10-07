package fr.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.projet.app.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
	@Query("SELECT t FROM Role t WHERE t.idRole=?1") //custom query
	public Role findById(int id);
}
