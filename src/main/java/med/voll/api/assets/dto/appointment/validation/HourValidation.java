package med.voll.api.assets.dto.appointment.validation;

import med.voll.api.assets.dto.appointment.AppointmentScheduleData;
import med.voll.api.core.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HourValidation {

    public void validateIsCorrectHour(AppointmentScheduleData data) {
        var consultationDate = data.date();
        var now = LocalDateTime.now();
        var differenceBetweenMinutes = Duration.between(now,consultationDate).toMinutes();

        if (differenceBetweenMinutes < 30) {
            throw new ValidationException("Consultation must be scheduled 30 minutes in advance!");
        }
    }
}
