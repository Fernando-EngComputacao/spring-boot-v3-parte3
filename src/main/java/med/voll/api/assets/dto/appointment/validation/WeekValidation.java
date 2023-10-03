package med.voll.api.assets.dto.appointment.validation;

import med.voll.api.assets.dto.appointment.AppointmentScheduleData;
import med.voll.api.assets.dto.appointment.validation.interfaces.AppointmentValidationInterface;
import med.voll.api.core.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class WeekValidation implements AppointmentValidationInterface {

    public void validate(AppointmentScheduleData data) {
        var consultationDate = data.date();
        var sunday = consultationDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeHourOpen = consultationDate.getHour() < 7; // 07 AM
        var afterHourOpen = consultationDate.getHour() > 18; // 18PM

        if (sunday || beforeHourOpen || afterHourOpen) {
            throw new ValidationException("Consultation outside opening hours!");
        }
    }
}
