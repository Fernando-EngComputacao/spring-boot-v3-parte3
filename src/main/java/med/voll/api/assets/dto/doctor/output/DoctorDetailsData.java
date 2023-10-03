package med.voll.api.assets.dto.doctor.output;

import med.voll.api.assets.dto.doctor.Specialty;
import med.voll.api.assets.entity.Address;
import med.voll.api.assets.entity.Doctor;

public record DoctorDetailsData(
        Long id,
        String name,
        String email,
        String crm,
        Specialty specialty,
        Address address
) {
    public DoctorDetailsData(Doctor med) {
        this(med.getId(), med.getName(), med.getEmail(), med.getCrm(), med.getSpecialty(), med.getAddress());
    }
}
