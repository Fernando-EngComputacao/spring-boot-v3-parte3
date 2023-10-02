package med.voll.api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

public record AppointmentScheduleData(
    Long idDoctor,
    @NotNull
    Long idPatient,
    @NotNull
    @Future
    LocalDateTime date
) {
}
