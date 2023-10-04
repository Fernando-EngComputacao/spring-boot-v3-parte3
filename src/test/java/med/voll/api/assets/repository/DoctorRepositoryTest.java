package med.voll.api.assets.repository;

import lombok.extern.slf4j.Slf4j;
import med.voll.api.assets.dto.address.AddressData;
import med.voll.api.assets.dto.doctor.Specialty;
import med.voll.api.assets.dto.doctor.input.DoctorRegisterData;
import med.voll.api.assets.dto.patient.input.RegisterDataPatient;
import med.voll.api.assets.entity.Appointment;
import med.voll.api.assets.entity.Doctor;
import med.voll.api.assets.entity.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
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

    @Test @DisplayName("Deve retornar nulo quando unico doctor cadastrado nao esta disponivel na data")
    void scene01_randonChosenDoctorOnRightDate() {
        //given or arrange
        var nextMondayAs10 = LocalDate
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        //when or act
        var doctor = registerDoctor("Fernando", "fernando.furtado@gmail.com", "293694", Specialty.ORTOPEDIA);
        var patient = registerPatient("Lucas", "lucas@gmail.com", "839.768.865-43");
        registerAppointment(doctor, patient, nextMondayAs10);

        //then or assert
        var doctorFree = repository.randonChosenDoctorOnRightDate(Specialty.ORTOPEDIA, nextMondayAs10);
        assertThat(doctorFree).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data pela especialidade")
    void scene02_randonChosenDoctorOnRightDate() {
        //given or arrange
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        //when or act
        var doctor = registerDoctor("Doctor2", "doctor2@voll.med", "402845", Specialty.ORTOPEDIA);

        //then or assert
        var freeDoctor = repository.randonChosenDoctorOnRightDate(Specialty.ORTOPEDIA, nextMondayAt10);
        assertThat(freeDoctor).isEqualTo(doctor);
    }

    @Test
    @DisplayName("Deveria devolver null quando tenta registrar consulta no domingo")
    void scene03_randonChosenDoctorOnRightDate() {
        //given or arrange
        var nextSundayAt14 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
                .atTime(14, 0);

        //when or act
        var doctor = registerDoctor("Doctor2", "doctor2@voll.med", "402845", Specialty.ORTOPEDIA);
        var patient = registerPatient("Lucas", "lucas@gmail.com", "839.768.865-43");
        registerAppointment(doctor, patient, nextSundayAt14);

        //then or assert
        var freeDoctor = repository.randonChosenDoctorOnRightDate(Specialty.ORTOPEDIA, nextSundayAt14);
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Deveria devolver null quando tenta registrar consulta antes do horário comercial")
    void scene04_randonChosenDoctorOnRightDate() {
        //given or arrange
        var nextWednesdeyAt5AM = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY))
                .atTime(5, 0);

        //when or act
        var doctor = registerDoctor("Doctor2", "doctor2@voll.med", "402845", Specialty.ORTOPEDIA);
        var patient = registerPatient("Lucas", "lucas@gmail.com", "839.768.865-43");
        registerAppointment(doctor, patient, nextWednesdeyAt5AM);

        //then or assert
        var freeDoctor = repository.randonChosenDoctorOnRightDate(Specialty.ORTOPEDIA, nextWednesdeyAt5AM);
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Deveria devolver null quando tenta registrar consulta após o horário comercial")
    void scene05_randonChosenDoctorOnRightDate() {
        //given or arrange
        var nextWednesdeyAt22PM = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY))
                .atTime(22, 0);

        //when or act
        var doctor = registerDoctor("Doctor2", "doctor2@voll.med", "402845", Specialty.ORTOPEDIA);
        var patient = registerPatient("Lucas", "lucas@gmail.com", "839.768.865-43");
        registerAppointment(doctor, patient, nextWednesdeyAt22PM);

        //then or assert
        var freeDoctor = repository.randonChosenDoctorOnRightDate(Specialty.ORTOPEDIA, nextWednesdeyAt22PM);
        assertThat(freeDoctor).isNull();
    }

    //Others Methods
    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime data) {
        em.persist(new Appointment(null, doctor, patient, data));
    }

    private Doctor registerDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorRegisterData(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf) {
        var patient = new Patient(dadosPatient(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DoctorRegisterData doctorRegisterData(String name, String email, String crm, Specialty specialty) {
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