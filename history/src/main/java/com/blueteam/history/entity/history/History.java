package com.blueteam.history.entity.history;

import com.blueteam.history.entity.Patient;
import com.blueteam.history.entity.history.exam.Exam;
import com.blueteam.history.entity.history.treatment.ConservativeTreatment;
import com.blueteam.history.entity.history.treatment.SurgicalTreatment;
import lombok.Data;
import lombok.Getter;


import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "history")
@Data
public class History {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "conservative_treatment")
    private ConservativeTreatment conservativeTreatment;

    @OneToOne
    @JoinColumn(name = "surgical_treatment")
    private SurgicalTreatment surgicalTreatment;

    @OneToOne
    @JoinColumn(name = "exam")
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patient;

}
