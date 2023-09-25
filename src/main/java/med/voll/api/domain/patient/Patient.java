package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.patient.input.UpdateDataPatient;
import med.voll.api.domain.patient.input.RegisterDataPatient;

@Table(name = "patients")
@Entity(name = "patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    private String telephone;

    private String cpf;

    @Embedded
    private Address address;

    private Boolean active;

    public Patient(RegisterDataPatient dados) {
        this.active = true;
        this.name = dados.name();
        this.email = dados.email();
        this.telephone = dados.telephone();
        this.cpf = dados.cpf();
        this.address = new Address(dados.address());
    }

    public void patientUpdateInformations(UpdateDataPatient dados) {
        if (dados.name() != null) {
            this.name = dados.name();
        }
        if (dados.telephone() != null) {
            this.telephone = dados.telephone();
        }
        if (dados.address() != null) {
            this.address.updateInfo(dados.address());
        }

    }

    public void deleteLogical() {
        this.active = false;
    }
}
