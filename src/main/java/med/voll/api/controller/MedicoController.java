package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping("/search")
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping("/status")
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @GetMapping
    public Page<DadosListagemMedico> findAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable page){
        return repository.findAll(page).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @jakarta.transaction.Transactional
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }

    @DeleteMapping("/logical/{id}")
    @jakarta.transaction.Transactional
    public ResponseEntity logicalDelete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.excluir();
        return ResponseEntity.noContent().build();
    }


}
