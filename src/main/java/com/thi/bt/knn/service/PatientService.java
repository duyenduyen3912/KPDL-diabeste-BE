package com.thi.bt.knn.service;

import com.thi.bt.knn.Patient;
import com.thi.bt.knn.User;
import com.thi.bt.knn.repository.PatientRepository;
import com.thi.bt.knn.request.SearchModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> findPatientByName(String name) {
        return patientRepository.findByName(name);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> search(SearchModal searchModal) {
        User userLogin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return patientRepository.search(searchModal.getFromDate(), searchModal.getToDate(), userLogin.getId());
    }
}