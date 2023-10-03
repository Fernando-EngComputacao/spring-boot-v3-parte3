package med.voll.api.assets.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import med.voll.api.assets.service.AppointmentService;
import med.voll.api.assets.dto.appointment.AppointmentDetailsData;
import med.voll.api.assets.dto.appointment.AppointmentScheduleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/appointment")
@EnableMethodSecurity(securedEnabled = true)
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @PostMapping()
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid AppointmentScheduleData data) {
        return ResponseEntity.ok(service.schedule(data));

    }

}