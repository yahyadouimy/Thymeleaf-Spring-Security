package ma.enset.hopital;

import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repositories.PatientRp;
import ma.enset.hopital.security.repositories.UserComptesRepo;
import ma.enset.hopital.security.service.UserCompteService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@AllArgsConstructor
public class SpringMvc2Application {
    UserComptesRepo userComptesRepo;
    public static void main(String[] args) {
        SpringApplication.run(SpringMvc2Application.class, args);
    }

   // @Bean
    CommandLineRunner start(PatientRp patient){
        return args -> {


            for(int i=0;i<30;i++){
                Patient p=new Patient();
                p.setNom("amine"+i);
                p.setEmail("emial@e"+i);
                p.setPhone("phone"+i);
                patient.save(p);

            }
        };
    };

    @Bean
    CommandLineRunner users(UserCompteService userCompteService){

        return args ->{


            userCompteService.addNewRole("USER");
            userCompteService.addNewRole("ADMIN");

            userCompteService.addNewUser("user1","user1@gm","1234","1234");
            userCompteService.addNewUser("user2","user2@gm","1234","1234");
            userCompteService.addNewUser("admin","admin@gm","1234","1234");
            userCompteService.addRoleToUser("user1","USER");
            userCompteService.addRoleToUser("user2","USER");
            userCompteService.addRoleToUser("admin","ADMIN");
            userCompteService.addRoleToUser("admin","USER");

        };
    };
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
