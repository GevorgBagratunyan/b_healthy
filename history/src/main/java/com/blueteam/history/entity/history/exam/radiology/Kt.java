package com.blueteam.history.entity.history.exam.radiology;

import com.blueteam.history.entity.Doctor;
import com.blueteam.history.entity.Patient;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "kt")
@Data
public class Kt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "coclusion")
    private String conclusion;

    @Column(name = "kt_date")
    private LocalDate ktDate;

}
