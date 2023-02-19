package com.practiceSpring.Spring.Tutorial.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseMaterial {
    @Id
    @SequenceGenerator(
            name = "courseMa_sequence",
            sequenceName = "courseMa_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "courseMa_sequence")
    private Long courseMaId;
    private String url;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "id"
    )
    private Course course;
}
