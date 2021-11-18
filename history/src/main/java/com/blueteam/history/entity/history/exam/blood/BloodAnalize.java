package com.blueteam.history.entity.history.exam.blood;

import com.blueteam.history.entity.history.exam.Exam;

import javax.persistence.*;
import java.util.List;

@Entity
public class BloodAnalize {

    @Id
    private Long id;

    @OneToMany
    @JoinColumn(name = "blood_analize_id")
    private List<BloodChemistry> chemistries;

    @OneToMany
    @JoinColumn(name = "blood_analize_id")
    private List<BloodGeneral>  bloodGenerals;

}
