package med.voll.api.assets.dto.appointment.validation;

import med.voll.api.assets.dto.appointment.AppointmentScheduleData;
import med.voll.api.assets.dto.appointment.validation.interfaces.AppointmentValidationInterface;
import med.voll.api.assets.repository.PatientRepository;
import med.voll.api.core.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientActiveValidation implements AppointmentValidationInterface {
    @Autowired
    private PatientRepository repository;

    public void validate(AppointmentScheduleData data) {
        if (data.idPatient() == null) {
            return;
        }

        var patientActived = repository.findActiveById(data.idPatient());
        if (!patientActived) {
            throw new ValidationException("Consultation cannot be scheduled with an inactive patient!");
        }
    }
}
