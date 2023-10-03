package med.voll.api.assets.dto.appointment.validation;

import med.voll.api.assets.dto.appointment.AppointmentScheduleData;
import med.voll.api.assets.dto.appointment.validation.interfaces.AppointmentValidationInterface;
import med.voll.api.assets.repository.AppointmentRepository;
import med.voll.api.core.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConflictAppointmentValidation implements AppointmentValidationInterface {

    @Autowired
    private AppointmentRepository repository;

    public void validate(AppointmentScheduleData data) {
        var conflicTime = repository.existsByDoctorIdAndDate(data.idDoctor(), data.date());

        if (conflicTime) {
            throw new ValidationException("Doctor already has another appointment at this same time!");
        }
    }
}
