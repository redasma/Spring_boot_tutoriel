package com.practiceSpring.Spring.Tutorial.service;

import com.practiceSpring.Spring.Tutorial.Repository.DepartementRepository;
import com.practiceSpring.Spring.Tutorial.entity.Departement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartementServiceTest {

    @Autowired
    private DepartementService service;

    @MockBean
    private DepartementRepository repository;

    @BeforeEach
    void setUp() {
        Departement departement= Departement.builder().departementName("sc")
                .departementAddress("montpellier")
                .departementCode("456")
                .build();
        Mockito.when(repository.findByDepartementNameIgnoreCase("sc")).thenReturn(departement);
    }

    @Test
    @DisplayName("get data based on valid departemnt name")
    public void whenValidDepartementName_thenDepartementShouldFound(){
     String departementName = "sc";
        Departement found  = service.findDepartementByName(departementName);
        assertEquals(departementName, found.getDepartementName());
    }
}