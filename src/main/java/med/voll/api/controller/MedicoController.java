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
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctor")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping("/search")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        Medico medico = new Medico(dados);
        repository.save(medico);
        URI uri = uriBuilder.path("/doctor/search/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping("/status")
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> findAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable page){
        return ResponseEntity.ok(repository.findAll(page).map(DadosListagemMedico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        Medico medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @jakarta.transaction.Transactional
    public ResponseEntity delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logical/{id}")
    @jakarta.transaction.Transactional
    public ResponseEntity logicalDelete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.excluir();
        return ResponseEntity.noContent().build();
    }


}
