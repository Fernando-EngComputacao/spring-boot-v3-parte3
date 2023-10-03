package med.voll.api.assets.dto.doctor.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.assets.dto.address.AddressData;
import med.voll.api.assets.dto.doctor.Specialty;

public record DoctorRegisterData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,

        @NotBlank
        String telephone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Specialty specialty,

        @NotNull @Valid AddressData address) {
}
