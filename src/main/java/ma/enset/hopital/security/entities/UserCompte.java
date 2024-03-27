package ma.enset.hopital.security.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class UserCompte {
    @Id
    private String id;
    private  String username;
    private  String email;
    private  String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> roles;


}
