package med.voll.api.domain.patient.input;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;

public record UpdateDataPatient(
        @NotNull
        Long id,
        String name,
        String telephone,
        AddressData address) {
}
