package fr.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.projet.app.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>
{
	@Query("SELECT t FROM Role t WHERE t.idRole=?1")
	public Role findById(int id);
}
