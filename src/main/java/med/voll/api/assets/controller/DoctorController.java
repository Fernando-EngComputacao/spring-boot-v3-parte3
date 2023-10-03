package med.voll.api.assets.controller;

import jakarta.validation.Valid;
import med.voll.api.assets.dto.doctor.input.DoctorRegisterData;
import med.voll.api.assets.dto.doctor.input.DoctorUpdateData;
import med.voll.api.assets.dto.doctor.output.DoctorAllData;
import med.voll.api.assets.dto.doctor.output.DoctorDetailsData;
import med.voll.api.assets.entity.Doctor;
import med.voll.api.assets.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctor")
@EnableMethodSecurity(securedEnabled = true)
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping("/search")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DoctorRegisterData dados, UriComponentsBuilder uriBuilder) {
        Doctor doctor = new Doctor(dados);
        repository.save(doctor);
        URI uri = uriBuilder.path("/doctor/search/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetailsData(doctor));
    }

    @GetMapping("/status")
    public ResponseEntity<Page<DoctorAllData>> listar(@PageableDefault(size = 10, sort = {"name"}) Pageable page) {
        return ResponseEntity.ok(repository.findAllByActiveTrue(page).map(DoctorAllData::new));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorAllData>> listById(@PageableDefault(size = 10, sort = {"name"}) Pageable page) {
        return ResponseEntity.ok(repository.findAllByActiveTrue(page).map(DoctorAllData::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity findAll(@PathVariable Long id, @PageableDefault(size = 10, sort = {"name"}) Pageable page) {
        Doctor doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailsData(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DoctorUpdateData dados) {
        Doctor doctor = repository.getReferenceById(dados.id());
        doctor.updateInformations(dados);
        return ResponseEntity.ok(new DoctorDetailsData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Secured("ROLE_ADMIN")
    public ResponseEntity delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/logical/{id}")
    @Transactional
    public ResponseEntity logicalDelete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.excluir();
        return ResponseEntity.noContent().build();
    }


}
