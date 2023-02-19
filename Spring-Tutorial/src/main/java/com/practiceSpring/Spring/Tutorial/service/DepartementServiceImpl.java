package com.practiceSpring.Spring.Tutorial.service;


import com.practiceSpring.Spring.Tutorial.Repository.DepartementRepository;
import com.practiceSpring.Spring.Tutorial.entity.Departement;
import com.practiceSpring.Spring.Tutorial.error.DepartementNotFoundExcepition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements DepartementService{

    @Autowired
    private DepartementRepository repository;

    @Override
    public Departement saveDepartement(Departement departement) {
        return repository.save(departement);
    }

    @Override
    public List<Departement> fetchDepartementsList() {
        return repository.findAll();
    }

    @Override
    public Departement fetchDepartementById(Long id) throws DepartementNotFoundExcepition {
        Optional<Departement> departement =  repository.findById(id);
        if(!departement.isPresent()){
            throw new DepartementNotFoundExcepition("departement not found");
        }
        return departement.get();
    }

    @Override
    public void deleteDepartement(Long id) {
        repository.deleteById(id);

    }

    @Override
    public Departement updateDepartements(Long id, Departement departemnt) {
        Departement depDb = repository.findById(id).get();
        if(Objects.nonNull(departemnt.getDepartementName()) && !"".equalsIgnoreCase(departemnt.getDepartementName())){
            depDb.setDepartementName(departemnt.getDepartementName());
        }
        if(Objects.nonNull(departemnt.getDepartementAddress()) && !"".equalsIgnoreCase(departemnt.getDepartementAddress())){
            depDb.setDepartementAddress(departemnt.getDepartementAddress());
        }
        if(Objects.nonNull(departemnt.getDepartementCode()) && !"".equalsIgnoreCase(departemnt.getDepartementCode())){
            depDb.setDepartementCode(departemnt.getDepartementCode());
        }
        return repository.save(depDb);
    }

    @Override
    public Departement findDepartementByName(String departementName) {
        return repository.findByDepartementNameIgnoreCase(departementName);
    }
}
