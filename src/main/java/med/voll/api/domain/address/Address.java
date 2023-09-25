package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String place;
    private String neighborhood;
    private String cep;
    private String number;
    private String complement;
    private String city;
    private String uf;

    public Address(AddressData dados) {
        this.place = dados.place();
        this.neighborhood = dados.neighborhood();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.city = dados.city();
        this.number = dados.number();
        this.complement = dados.complement();
    }

    public void updateInfo(AddressData dados) {
        if (dados.place() != null) {
            this.place = dados.place();
        }
        if (dados.neighborhood() != null) {
            this.neighborhood = dados.neighborhood();
        }
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
        if (dados.city() != null) {
            this.city = dados.city();
        }
        if (dados.number() != null) {
            this.number = dados.number();
        }
        if (dados.complement() != null) {
            this.complement = dados.complement();
        }
    }
}
