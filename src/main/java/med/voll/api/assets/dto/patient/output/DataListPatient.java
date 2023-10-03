package med.voll.api.assets.dto.patient.output;

import med.voll.api.assets.entity.Patient;

public record DataListPatient(Long id, String name, String email, String cpf) {

    public DataListPatient(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }

}
