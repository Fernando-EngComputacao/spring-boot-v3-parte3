package med.voll.api.assets.dto.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;

import java.time.LocalDateTime;

public record AppointmentDetailsData(
        Long id,

        Long idDoctor,
        Long idPatient,
        LocalDateTime date
) {
}
