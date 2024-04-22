package com.pplanaturmo.inrappproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.model.Professional;
import com.pplanaturmo.inrappproject.model.User;
import com.pplanaturmo.inrappproject.repository.ProfessionalRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProfessionalService {


    @Autowired
    private ProfessionalRepository professionalRepository;

     
     public Professional createObservation(Professional professional) {
        return professionalRepository.save(professional);
    }

   
    public List<Professional> getAllObservations() {
        return professionalRepository.findAll();
    }

    public Professional getObservationById(Long id) {
        return professionalRepository.findById(id).orElse(null);
    }

    public Professional updateObservation(Professional professional) {
        return professionalRepository.save(professional);
    }

    public void deleteObservation(Long id) {
        professionalRepository.deleteById(id);
    }

    public List<User> getSupervisedUsers(Long professionalId) {
    
        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        //Mejor con excepciones??
        return professional.getSupervisedUsers();
    }
}
