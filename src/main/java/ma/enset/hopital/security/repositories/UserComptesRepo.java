package ma.enset.hopital.security.repositories;

import ma.enset.hopital.security.entities.UserCompte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserComptesRepo extends JpaRepository<UserCompte,String> {
    UserCompte findByUsername(String username);
}
