package med.voll.api.domain.doctor.output;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.doctor.Specialty;
import med.voll.api.domain.doctor.Doctor;

public record DoctorDetailsData(
        Long id,
        String nome,
        String email,
        String crm,
        Specialty specialty,
        Address address
) {
    public DoctorDetailsData(Doctor med) {
        this(med.getId(), med.getName(), med.getEmail(), med.getCrm(), med.getSpecialty(), med.getAddress());
    }
}
