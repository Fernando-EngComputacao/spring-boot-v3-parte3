package med.voll.api.assets.dto.patient.input;

import jakarta.validation.constraints.NotNull;
import med.voll.api.assets.dto.address.AddressData;

public record UpdateDataPatient(
        @NotNull
        Long id,
        String name,
        String telephone,
        AddressData address) {
}
