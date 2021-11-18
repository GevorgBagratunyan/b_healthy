package com.blueteam.history.entity.history.treatment;

import com.blueteam.history.entity.Doctor;
import com.blueteam.history.entity.Patient;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "surgical_treatment")
@Data
public class SurgicalTreatment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private  Long id;
    @Column(name = "date")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "surgical_id")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name= "surgical_treatment")
    private String surgicalTreatment;

}
