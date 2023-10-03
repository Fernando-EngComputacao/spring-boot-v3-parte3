package med.voll.api.assets.dto.doctor.input;

import jakarta.validation.constraints.NotNull;
import med.voll.api.assets.dto.address.AddressData;

public record DoctorUpdateData(
        @NotNull
        Long id,
        String name,
        String telephone,
        AddressData address) {
}
