package com.blueteam.history.entity.history.treatment;

import com.blueteam.history.entity.Doctor;
import com.blueteam.history.entity.Patient;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

import static javax.persistence.GenerationType.*;


@Entity
@Table(name = "cnservative_treatment")
@Data
public class ConservativeTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "conservative_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "treaatment")
    private String treatment;

    @Column(name = "date")
    private LocalDate date;

}
