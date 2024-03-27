package ma.enset.hopital.repositories;

import ma.enset.hopital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRp extends JpaRepository<Patient,Long> {
    Page<Patient> findByNomContains(String kw, Pageable page);
}
