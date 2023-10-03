package med.voll.api.assets.repository;

import jakarta.validation.constraints.Null;
import med.voll.api.assets.entity.Doctor;
import med.voll.api.assets.dto.doctor.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable paginacao);

    @Query("select d.active from doctor d where d.id = :idDoctor")
    Boolean findActiveById(Long idDoctor);

    @Query("""
            select d from doctor d where d.active = true and  d.specialty = :specialtyData
            and d.id not in(
                select a.doctor.id from appointment a where a.date = :dateData
            )
            order by rand() limit 1
            """)
    Doctor randonChosenDoctorOnRightDate(Specialty specialtyData, LocalDateTime dateData);
}
