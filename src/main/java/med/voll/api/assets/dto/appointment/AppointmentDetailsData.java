package med.voll.api.assets.dto.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;
import med.voll.api.assets.entity.Appointment;

import java.time.LocalDateTime;

public record AppointmentDetailsData(
        Long id,

        Long idDoctor,
        Long idPatient,
        LocalDateTime date
) {
    public AppointmentDetailsData(Appointment app) {
        this(app.getId(),
            app.getDoctor().getId(),
            app.getPatient().getId(),
                    app.getDate()
        );
    }
}
