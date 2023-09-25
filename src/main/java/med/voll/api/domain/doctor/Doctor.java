package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.doctor.input.DoctorUpdateData;
import med.voll.api.domain.doctor.input.DoctorRegisterData;

@Table(name = "doctors")
@Entity(name = "doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    private String telephone;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private Boolean active;

    public Doctor(DoctorRegisterData dados) {
        this.active = true;
        this.name = dados.nome();
        this.email = dados.email();
        this.telephone = dados.telefone();
        this.crm = dados.crm();
        this.specialty = dados.specialty();
        this.address = new Address(dados.endereco());
    }

    public void updateInformations(DoctorUpdateData dados) {
        if (dados.nome() != null) {
            this.name = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telephone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.address.updateInfo(dados.endereco());
        }

    }

    public void excluir() {
        this.active = false;
    }
}
