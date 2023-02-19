package com.practiceSpring.Spring.Tutorial.Repository;

import com.practiceSpring.Spring.Tutorial.entity.Departement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartementRepositoryTest {

    @Autowired
    private DepartementRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Departement departement = Departement.builder().departementName("lettre")
                .departementCode("hhhh")
                .departementAddress("paris")
                .build();
        testEntityManager.persist(departement);
    }

    @Test
    public void whenfindByID_thenReturnDepartement() {
        Departement departement = repository.findById(1L).get();
        assertEquals(departement.getDepartementName(),"lettre");

    }


}