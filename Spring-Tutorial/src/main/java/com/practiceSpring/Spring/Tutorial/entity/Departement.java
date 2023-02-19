package com.practiceSpring.Spring.Tutorial.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departementID;

    @NotBlank(message= "Please add departement name")
    /*@Length(max= 10, min= 1)
    @Size(max= 10, min= 1)
    @Email
    @Positive
    @Negative*/
    private String departementName;
    private String departementAddress;
    private String departementCode;




}
