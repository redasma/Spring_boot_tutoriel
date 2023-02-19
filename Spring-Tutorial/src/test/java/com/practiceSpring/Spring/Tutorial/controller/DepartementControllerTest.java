package com.practiceSpring.Spring.Tutorial.controller;

import com.practiceSpring.Spring.Tutorial.entity.Departement;
import com.practiceSpring.Spring.Tutorial.error.DepartementNotFoundExcepition;
import com.practiceSpring.Spring.Tutorial.service.DepartementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartementController.class)
class DepartementControllerTest {

    @Autowired
    private MockMvc mvcMvc;

    @MockBean
    private DepartementService service;

    @Autowired
    private DepartementController controller;

    private Departement departement;

    @BeforeEach
    void setUp(){
        departement = Departement.builder()
                .departementName("lettre")
                .departementCode("L")
                .departementAddress("montpellier")
                .departementID(1L)
                .build();



    }

    @Test
    void fetchDepartementById() throws Exception {
        Departement inputdepartement = Departement.builder()
                .departementName("lettre")
                .departementCode("L")
                .departementAddress("montpellier")
                .departementID(1L)
                .build();
        Mockito.when(service.fetchDepartementById(inputdepartement.getDepartementID())).thenReturn(departement);

        /* mvcMvc.perform(MockMvcRequestBuilders.get("/departements/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departementName\": \"lettre\",\n" +
                        "\t\"departementAddress\": \"montpellier\",\n" +
                        "\t\"departementCode\": \"L\"\n" +
                        "}")).andExpect(MockMvcResultMatchers.status().isOk());*/

        mvcMvc.perform(MockMvcRequestBuilders.get("/departements/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departementName")
                        .value(departement.getDepartementName()));
    }
}