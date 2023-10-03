package med.voll.api.assets.controller;

import jakarta.validation.Valid;
import med.voll.api.assets.dto.patient.output.AllDataPatient;
import med.voll.api.assets.dto.patient.output.DataListPatient;
import med.voll.api.assets.entity.Patient;
import med.voll.api.assets.dto.patient.input.UpdateDataPatient;
import med.voll.api.assets.dto.patient.input.RegisterDataPatient;
import med.voll.api.assets.repository.PatientRepository;
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

@RestController
@RequestMapping("patient")
@EnableMethodSecurity(securedEnabled = true)
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterDataPatient dados, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(dados);
        repository.save(patient);

        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new AllDataPatient(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Secured("ROLE_ADMIN")
    public ResponseEntity remove(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.deleteLogical();

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<DataListPatient>> findAll(@PageableDefault(size = 10, sort = {"name"}) Pageable page) {
        var pageable = repository.findAllByActiveTrue(page).map(DataListPatient::new);
        return ResponseEntity.ok(pageable);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateDataPatient dados) {
        var patient = repository.getReferenceById(dados.id());
        patient.patientUpdateInformations(dados);

        return ResponseEntity.ok(new AllDataPatient(patient));
    }


}
