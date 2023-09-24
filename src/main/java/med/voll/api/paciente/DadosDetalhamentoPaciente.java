package med.voll.api.paciente;

import jakarta.persistence.Embedded;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.Medico;

public record DadosDetalhamentoPaciente(
    Long id,
    String nome,
    String email,

    String telefone,

    String cpf,

    Endereco endereco
){
    public DadosDetalhamentoPaciente(Paciente pac) {
        this(pac.getId(), pac.getNome(), pac.getEmail(), pac.getTelefone(), pac.getCpf(), pac.getEndereco());
    }
}
