package com.pplanaturmo.inrappproject.dosage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pplanaturmo.inrappproject.aggregatedDtos.DatesBetweenDto;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/api/dosage")
public class DosageController {

    @Autowired
    private DosageService dosageService;

    @GetMapping("/{id}")
    public ResponseEntity<Dosage> getDosageById(@PathVariable Long id) {
        Dosage dosage = dosageService.getDosageById(id);
        if (dosage == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dosage, HttpStatus.OK);
    }

    @GetMapping("/between-dates")
    public List<Dosage> getObservationsBetweenDates(@Valid @RequestBody DatesBetweenDto datesBetweenDto) {

        return dosageService.getDosagesBetweenDates(datesBetweenDto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Dosage>> getAllDosagesByUser(@PathVariable Long userId) {
        List<Dosage> dosages = dosageService.getAllDosagesByUser(userId);
        return new ResponseEntity<>(dosages, HttpStatus.OK);
    }

}
