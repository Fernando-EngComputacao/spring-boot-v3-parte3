package med.voll.api.domain.patient.output;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.patient.Patient;

public record AllDataPatient(
    Long id,
    String name,
    String email,

    String telephone,

    String cpf,

    Address address
){
    public AllDataPatient(Patient pac) {
        this(pac.getId(), pac.getName(), pac.getEmail(), pac.getTelephone(), pac.getCpf(), pac.getAddress());
    }
}
