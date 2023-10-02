package med.voll.api.assets.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import med.voll.api.domain.appointment.AppointmentDetailsData;
import med.voll.api.domain.appointment.AppointmentScheduleData;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/appointment")
@EnableMethodSecurity(securedEnabled = true)
public class AppointmentController {
    @PostMapping()
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid AppointmentScheduleData data) {
        log.info("**[APPOINTMENT-CONTROLLER] {}",data);
        return ResponseEntity.ok(new AppointmentDetailsData(null, null, null, null));

    }

}