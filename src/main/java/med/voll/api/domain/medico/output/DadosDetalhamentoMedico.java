package med.voll.api.domain.medico.output;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {
    public DadosDetalhamentoMedico(Medico med) {
        this(med.getId(), med.getNome(), med.getEmail(), med.getCrm(), med.getEspecialidade(), med.getEndereco());
    }
}
