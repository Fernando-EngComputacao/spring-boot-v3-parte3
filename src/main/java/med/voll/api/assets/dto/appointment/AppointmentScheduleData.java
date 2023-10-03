package med.voll.api.assets.dto.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.assets.dto.doctor.Specialty;

import java.time.LocalDateTime;

public record AppointmentScheduleData(
    @JsonAlias({"doctor_id", "id_doctor"})
    Long idDoctor,
    @NotNull
    @JsonAlias({"patient_id", "id_patient"}) // apelidos para o "idPatient" que pode vir do JSON
    Long idPatient,
    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    LocalDateTime date,
//    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
    Specialty specialty
) {
}
