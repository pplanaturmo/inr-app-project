package com.pplanaturmo.inrappproject.dosage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.dosage.dtos.DosageRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/dosage")
public class DosageController {


    @Autowired
    private DosageService dosageService;

    @PostMapping("/create")
    public ResponseEntity<Dosage> createDosage(@Valid @RequestBody DosageRequest dosageRequest) {
        Dosage dosage = convertToDosage(dosageRequest);
        Dosage createdDosage = dosageService.createDosage(dosage);
        return new ResponseEntity<>(createdDosage, HttpStatus.CREATED);
    }

    private Dosage convertToDosage(DosageRequest dosageRequest) {
        Dosage dosage = new Dosage();
        
        // dosage.setMeasurementId(dosageRequest.getMeasurementId());
        dosage.setDoseDate(dosageRequest.getDoseDate());
        dosage.setTaken(dosageRequest.getTaken());
        return dosage;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dosage> getDosageById(@PathVariable Long id) {
        Dosage dosage = dosageService.getDosageById(id);
        if (dosage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dosage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDosage(@PathVariable Long id) {
        dosageService.deleteDosage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Dosage>> getAllDosagesByUser(@PathVariable Long userId) {
        List<Dosage> dosages = dosageService.getAllDosagesByUser(userId);
        return new ResponseEntity<>(dosages, HttpStatus.OK);
    }


}
