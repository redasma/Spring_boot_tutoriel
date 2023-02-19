package com.practiceSpring.Spring.Tutorial.service;

import com.practiceSpring.Spring.Tutorial.entity.Departement;
import com.practiceSpring.Spring.Tutorial.error.DepartementNotFoundExcepition;

import java.util.List;
import java.util.Optional;

public interface DepartementService {
    public Departement saveDepartement(Departement departement);
    public List<Departement> fetchDepartementsList();
    public Departement fetchDepartementById(Long id) throws DepartementNotFoundExcepition;
    public void deleteDepartement(Long id);
    public Departement updateDepartements(Long id,Departement departemnt);
    public Departement findDepartementByName(String departementName);

}
