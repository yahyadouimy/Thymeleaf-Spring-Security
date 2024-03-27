package ma.enset.hopital.security.repositories;

import ma.enset.hopital.security.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Roles,String> {

    Roles findByRole(String role);
}
