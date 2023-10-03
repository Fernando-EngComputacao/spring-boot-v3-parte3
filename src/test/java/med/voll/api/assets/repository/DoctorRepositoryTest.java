package med.voll.api.assets.repository;

import lombok.extern.slf4j.Slf4j;
import med.voll.api.assets.dto.address.AddressData;
import med.voll.api.assets.dto.doctor.Specialty;
import med.voll.api.assets.dto.doctor.input.DoctorRegisterData;
import med.voll.api.assets.dto.patient.input.RegisterDataPatient;
import med.voll.api.assets.entity.Appointment;
import med.voll.api.assets.entity.Doctor;
import med.voll.api.assets.entity.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test @DisplayName("Deve retornar não nulo quando unico doctor cadastrado nao esta disponivel na data")
    void scene01_randonChosenDoctorOnRightDate() {
        var nextMondayAs15 = LocalDateTime
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .toLocalDate().atTime(15,0);

        var doctor = registerDoctor("Fernando", "fernando.furtado@gmail.com", "293694", Specialty.CARDIOLOGIA);
        var patient = registerPatient("Lucas", "lucas@gmail.com", "839.768.865-43");
        registerAppointment(doctor, patient, nextMondayAs15);

        var doctorFree = repository.randonChosenDoctorOnRightDate(Specialty.DERMATOLOGIA, nextMondayAs15);
        log.info("\n**[LOG] {}", doctorFree.toString());
        assertThat(doctorFree).isNotNull();
    }

    //Others Methods
    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime data) {
        em.persist(new Appointment(null, doctor, patient, data));
    }

    private Doctor registerDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(dadosDoctor(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(dadosPatient(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DoctorRegisterData dadosDoctor(String name, String email, String crm, Specialty specialty) {
        return new DoctorRegisterData(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                addressData()
        );
    }

    private RegisterDataPatient dadosPatient(String name, String email, String cpf) {
        return new RegisterDataPatient(
                name,
                email,
                "61999999999",
                cpf,
                addressData()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}