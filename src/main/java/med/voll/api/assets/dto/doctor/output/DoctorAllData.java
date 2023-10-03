package med.voll.api.assets.dto.doctor.output;

import med.voll.api.assets.dto.doctor.Specialty;
import med.voll.api.assets.entity.Doctor;

public record DoctorAllData(Long id, String name, String email, String crm, Specialty specialty) {

    public DoctorAllData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }

}
