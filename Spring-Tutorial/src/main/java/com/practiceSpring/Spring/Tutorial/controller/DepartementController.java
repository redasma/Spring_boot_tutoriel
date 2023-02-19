package com.practiceSpring.Spring.Tutorial.controller;

import com.practiceSpring.Spring.Tutorial.entity.Departement;
import com.practiceSpring.Spring.Tutorial.error.DepartementNotFoundExcepition;
import com.practiceSpring.Spring.Tutorial.service.DepartementService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartementController {

    @Autowired
    private DepartementService service;
    private final Logger logger = LoggerFactory.getLogger(DepartementController.class);

    @PostMapping("/departements")
    public Departement saveDepartement(@Valid @RequestBody Departement departement){
        logger.info("saving departements");
        return service.saveDepartement(departement);
    }

    @GetMapping("/departements")
    public List<Departement> fetchDepartements(){
        return service.fetchDepartementsList();
    }

    @GetMapping("/departements/{id}")
    public Departement fetchDepartementById(@PathVariable("id") Long id) throws DepartementNotFoundExcepition {
        return service.fetchDepartementById(id);
    }

    @DeleteMapping("/departements/{id}")
    public String deleteDepartementById(@PathVariable("id") Long id){
        service.deleteDepartement(id);
        return "departement deleted";
    }

    @PutMapping("/departements/{id}")
    public Departement updateDepartement(@PathVariable("id") Long Id, @RequestBody  Departement departemnt){
        return service.updateDepartements(Id,departemnt);
    }

    @GetMapping("/departements/name/{name}")
    public Departement fetchDepartementbyName(@PathVariable("name") String name){
        return service.findDepartementByName(name);
    }
}