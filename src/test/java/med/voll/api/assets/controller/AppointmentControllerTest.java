package med.voll.api.assets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import med.voll.api.assets.dto.appointment.AppointmentDetailsData;
import med.voll.api.assets.dto.appointment.AppointmentScheduleData;
import med.voll.api.assets.dto.doctor.Specialty;
import med.voll.api.assets.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentScheduleData> jsonEntry;

    @Autowired
    private JacksonTester<AppointmentDetailsData> jsonReturn;

    @MockBean
    private AppointmentService service;

    private String urlTemplate = "/appointment";

    @Test
    @WithMockUser
    @DisplayName("Devolve codigo 400 quando ocorre informacoes invalidas")
    void schedule_scene1() throws Exception {
        var response = mvc.perform(post(urlTemplate))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @WithMockUser
    @DisplayName("Devolve codigo 200 quando ocorre informacoes validas")
    void schedule_scene2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.ORTOPEDIA;
        var detailsDataAppointment = new AppointmentDetailsData (null, 2l, 5l, date);

        when(service.schedule(any()))
                .thenReturn (detailsDataAppointment);

        var response = mvc.perform(
                post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonEntry.write(
                                new AppointmentScheduleData(2l,5l, date, specialty )
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonWaited = jsonReturn.write(detailsDataAppointment).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonWaited);
    }
}