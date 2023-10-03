package med.voll.api.assets.dto.patient.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.assets.dto.address.AddressData;

public record RegisterDataPatient(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,

        @NotBlank
        String telephone,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,

        @NotNull @Valid AddressData address) {
}
