package ma.enset.hopital.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotEmpty @Size(min =3,max = 30)
    private  String nom;
    @Email
    private String email;

    @NotEmpty(message = "ne doit pas etre vide") @Size(min =5,max = 30)
    private  String phone;

}
