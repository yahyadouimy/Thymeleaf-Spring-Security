package ma.enset.hopital.security.service;
import ma.enset.hopital.security.entities.UserCompte;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


   private IUserCompteService iUserCompteService;


            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                UserCompte userCompte=iUserCompteService.loadUserByUsername(username);
                if (userCompte==null) throw new UsernameNotFoundException("user not found");
                UserDetails userDetails= User
                        .withUsername(userCompte.getUsername())
                        .password(userCompte.getPassword())
                        .roles(userCompte.getRoles().stream().map(e->e.getRole()).toArray(String[]::new))
                        .build()
                        ;

                return userDetails;
            }
        };




