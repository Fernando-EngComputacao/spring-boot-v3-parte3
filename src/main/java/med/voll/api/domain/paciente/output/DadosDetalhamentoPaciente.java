package med.voll.api.domain.paciente.output;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.paciente.Paciente;

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
