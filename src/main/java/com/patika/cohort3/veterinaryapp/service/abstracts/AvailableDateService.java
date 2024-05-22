package com.patika.cohort3.veterinaryapp.service.abstracts;
import com.patika.cohort3.veterinaryapp.entity.AvailableDate;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AvailableDateService {
    public AvailableDate save(AvailableDate availableDate);
    public AvailableDate update(AvailableDate availableDate);
    public boolean delete(Long id);
    public AvailableDate getById(Long id);
    public List<AvailableDate> findAll();

}
