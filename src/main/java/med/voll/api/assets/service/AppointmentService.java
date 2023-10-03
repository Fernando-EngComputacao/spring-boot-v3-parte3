package med.voll.api.assets.service;

import med.voll.api.assets.dto.appointment.AppointmentDetailsData;
import med.voll.api.assets.dto.appointment.validation.interfaces.AppointmentValidationInterface;
import med.voll.api.assets.repository.AppointmentRepository;
import med.voll.api.assets.repository.DoctorRepository;
import med.voll.api.assets.repository.PatientRepository;
import med.voll.api.assets.entity.Appointment;
import med.voll.api.assets.dto.appointment.AppointmentScheduleData;
import med.voll.api.assets.entity.Doctor;
import med.voll.api.core.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<AppointmentValidationInterface> validator;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public AppointmentDetailsData schedule(AppointmentScheduleData data) {
        //validação de integridade
        if (!patientRepository.existsById(data.idPatient())){
            throw new ValidationException("Id of patient does not exist!");
        }
        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidationException("Id of doctor does not exist!");
        }
        //Validations ALL done
        validator.forEach(v -> v.validate(data));

        //Check
        var chosenDoctor = chooseDoctor(data);
        var appointment = Appointment
                .builder()
                .id(null)
                .doctor(
                        doctorRepository.getReferenceById(chosenDoctor.getId())
                )
                .patient(patientRepository.getReferenceById(data.idPatient()))
                .date(data.date())
                .build();
        appointmentRepository.save(appointment);

        return new AppointmentDetailsData(appointment);
    }

    private Doctor chooseDoctor(AppointmentScheduleData data) {
        if (data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());
        } else if (data.specialty() == null) {
            throw new ValidationException("Specialty needs to be informed!");
        }
        return doctorRepository.randonChosenDoctorOnRightDate(data.specialty(), data.date());

    }
}
