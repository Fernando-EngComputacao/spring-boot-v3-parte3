package med.voll.api.assets.dto.appointment.validation;

import med.voll.api.assets.dto.appointment.AppointmentScheduleData;
import med.voll.api.assets.dto.appointment.validation.interfaces.AppointmentValidationInterface;
import med.voll.api.assets.repository.AppointmentRepository;
import med.voll.api.core.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConflictPatientValidation implements AppointmentValidationInterface {
    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentScheduleData data) {
        var firstTime = data.date().withHour(7);
        var lastTime = data.date().withHour(18);
        var conflicTime = repository.existsByPatientIdAndDateBetween(data.idPatient(), firstTime, lastTime);

        if (conflicTime) {
            throw new ValidationException("Patient already has another appointment at this same time!");
        }
    }
}
