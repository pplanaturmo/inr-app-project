package com.pplanaturmo.inrappproject.professional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pplanaturmo.inrappproject.user.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProfessionalService {


    @Autowired
    private ProfessionalRepository professionalRepository;

     
     public Professional createProfessional(Professional professional) {
        return professionalRepository.save(professional);
    }

   
    public List<Professional> getAllProfessionals() {
        return professionalRepository.findAll();
    }

    public Professional getProfessionalById(Long id) {
        return professionalRepository.findById(id).orElse(null);
    }

    public Professional updateProfessional(Professional professional) {
        return professionalRepository.save(professional);
    }

    public void deleteProfessional(Long id) {
        professionalRepository.deleteById(id);
    }

    public List<User> getSupervisedUsers(Long professionalId) {
    
        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        return professional.getSupervisedUsers();
    }
}
