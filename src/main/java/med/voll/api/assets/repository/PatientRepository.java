package med.voll.api.assets.repository;

import med.voll.api.assets.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByActiveTrue(Pageable paginacao);

    @Query("select d.active from patient d where d.id = :idPatient")
    Boolean findActiveById(Long idPatient);
}
