package med.voll.api.medico;

import med.voll.api.endereco.Endereco;

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
