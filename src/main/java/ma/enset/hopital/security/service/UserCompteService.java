package ma.enset.hopital.security.service;

import ma.enset.hopital.security.entities.Roles;
import ma.enset.hopital.security.entities.UserCompte;
import ma.enset.hopital.security.repositories.RolesRepo;
import ma.enset.hopital.security.repositories.UserComptesRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserCompteService implements IUserCompteService {
    UserComptesRepo userComptesRepo;
    RolesRepo rolesRepo;
    PasswordEncoder passwordEncoder;



    @Override
    public UserCompte addNewUser(String username, String email,String password,  String passwordConfirm) {

        UserCompte u=userComptesRepo.findByUsername(username);
        if (u!=null) throw  new RuntimeException("user already exist");
        if(password!= passwordConfirm) throw  new RuntimeException("password not match");
         u=UserCompte.builder()
                .id(UUID.randomUUID().toString())
                .username(username)
                .email(email)
                .password(passwordEncoder.encode("123"))
                //.password("123")
                .build();
        UserCompte savedUser=userComptesRepo.save(u);
        return savedUser;
    }

    @Override
    public Roles addNewRole(String role) {
        Roles r=rolesRepo.findByRole(role);
        if (r!=null) throw new RuntimeException("role already exists");
        r=Roles.builder()
                .role(role)
                .build();
        return rolesRepo.save(r);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        UserCompte u=userComptesRepo.findByUsername(username);
        Roles r=rolesRepo.findByRole(role);
        if (u==null) throw  new RuntimeException("no user");
        if (r==null) throw new RuntimeException("no roles");
        u.getRoles().add(r);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {

    }


    @Override
    public UserCompte loadUserByUsername(String username){
        return userComptesRepo.findByUsername(username);
    };

}
