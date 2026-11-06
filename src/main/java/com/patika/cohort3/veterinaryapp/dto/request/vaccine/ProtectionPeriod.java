package com.patika.cohort3.veterinaryapp.dto.request.vaccine;

import java.time.LocalDate;

public interface ProtectionPeriod {
    LocalDate getProtectionStartDate();
    LocalDate getProtectionFinishDate();
}
