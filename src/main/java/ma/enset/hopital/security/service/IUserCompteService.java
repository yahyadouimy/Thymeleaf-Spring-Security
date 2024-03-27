package ma.enset.hopital.security.service;

import ma.enset.hopital.security.entities.Roles;
import ma.enset.hopital.security.entities.UserCompte;
import org.springframework.stereotype.Service;

@Service
public interface IUserCompteService {
    UserCompte addNewUser(String username,String email,String password,String passwordConfirm);
    Roles addNewRole(String role);
    void addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String role);
    UserCompte loadUserByUsername(String username);
}
