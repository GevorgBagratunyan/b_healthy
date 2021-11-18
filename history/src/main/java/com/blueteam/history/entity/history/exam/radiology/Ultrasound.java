package com.blueteam.history.entity.history.exam.radiology;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ultrasound")
@Data
public class Ultrasound {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "coclusion")
    private String coclusion;

    @Column(name = "date")
    private LocalDate ultrasoundDate;

}


