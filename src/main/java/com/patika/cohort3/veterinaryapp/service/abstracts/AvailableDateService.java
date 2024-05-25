package com.patika.cohort3.veterinaryapp.service.abstracts;
import com.patika.cohort3.veterinaryapp.entity.AvailableDate;

import java.util.List;


public interface AvailableDateService {
     AvailableDate save(AvailableDate availableDate);
     AvailableDate update(AvailableDate availableDate);
     boolean delete(Long id);
     AvailableDate getById(Long id);
     List<AvailableDate> findAll();

}
