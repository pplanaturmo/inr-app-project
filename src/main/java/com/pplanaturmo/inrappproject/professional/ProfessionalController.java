package com.pplanaturmo.inrappproject.professional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.user.User;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @PostMapping
    public Professional createProfessional(@Valid @RequestBody Professional professional) {
        return professionalService.createProfessional(professional);
    }

    @GetMapping
    public List<Professional> getAllProfessionals() {
        return professionalService.getAllProfessionals();
    }

    @GetMapping("/{id}")
    public Professional getProfessionalById(@PathVariable("id") @Valid @NotNull Long id) {
        return professionalService.getProfessionalById(id);
    }

    @PutMapping("/{id}")
    public Professional updateProfessional(@PathVariable("id") @Valid @NotNull Long id, @Valid @RequestBody Professional professional) {
        professional.setId(id);
        return professionalService.updateProfessional(professional);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessional(@PathVariable("id") @Valid @NotNull Long id) {
        professionalService.deleteProfessional(id);
    }

    @GetMapping("/{professionalId}/supervised-users")
    public List<User> getSupervisedUsers(@PathVariable Long professionalId) {
        return professionalService.getSupervisedUsers(professionalId);
    }
}
