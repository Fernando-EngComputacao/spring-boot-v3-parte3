package med.voll.api.assets.dto.appointment.validation.interfaces;

import med.voll.api.assets.dto.appointment.AppointmentScheduleData;

public interface AppointmentValidationInterface {
    void validate(AppointmentScheduleData data);
}
