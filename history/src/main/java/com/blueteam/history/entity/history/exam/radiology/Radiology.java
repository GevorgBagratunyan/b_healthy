package com.blueteam.history.entity.history.exam.radiology;

import com.blueteam.history.entity.history.exam.Exam;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "radiology")
public class Radiology {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "radiology")
    private List<Kt> kt;

    @OneToMany
    @JoinColumn(name = "radilogy_id")
    private List<Mrt> mrt;

    @OneToMany
    @JoinColumn(name = "radilogy_id")
    private List<Ultrasound> ultrasound;


    @OneToOne
    @JoinColumn(name = "radiology_id")
    private Exam exam;


}
