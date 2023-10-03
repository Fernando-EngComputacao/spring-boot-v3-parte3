package med.voll.api.assets.dto.appointment.validation;

import med.voll.api.assets.dto.appointment.AppointmentScheduleData;
import med.voll.api.assets.repository.DoctorRepository;
import med.voll.api.core.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorActiveValidation {

    @Autowired
    private DoctorRepository repository;

    public void validateDoctorIsActive(AppointmentScheduleData data) {
        if (data.idDoctor() == null) {
            return;
        }

        var doctorActived = repository.findActiveById(data.idDoctor());
        if (!doctorActived) {
            throw new ValidationException("Consultation cannot be scheduled with an inactive doctor!");
        }
    }
}
