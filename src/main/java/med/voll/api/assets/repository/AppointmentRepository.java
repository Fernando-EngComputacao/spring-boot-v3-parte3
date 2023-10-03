package med.voll.api.assets.repository;

import med.voll.api.assets.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorIdAndDate(Long idDoctor, LocalDateTime date);

    boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstTime, LocalDateTime lastTime);
}
