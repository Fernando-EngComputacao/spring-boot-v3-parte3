package med.voll.api.domain.doctor.output;

import med.voll.api.domain.doctor.Specialty;
import med.voll.api.domain.doctor.Doctor;

public record DoctorAllData(Long id, String name, String email, String crm, Specialty specialty) {

    public DoctorAllData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }

}
