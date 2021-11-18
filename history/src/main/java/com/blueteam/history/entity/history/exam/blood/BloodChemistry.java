package com.blueteam.history.entity.history.exam.blood;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table (name = "blood_chemistry")
@Data
public class BloodChemistry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "analyzed_date")
    private LocalDate analyzedDate;

    @Column(name = "m_creat")
    private double mCreatinine; //0.6 to 1.2 mg/dL

    @Column(name = "f_creat")
    private double fCreatinine; //0.5 to 1.3 mg/dL

    @Column(name = "glucose")
    private double glucose; //70 to 100 mg/dL

    @Column(name = "total_bil")
    private double totalBilirubin; //0.1 to 1.2 mg/dL

    @Column(name = "uncon_bil")
    private double unconjugatedBilirubin; // <0.9 mg/dl

    @Column(name = "con_bil")
    private double conjugatedBilirubin; // <0.3 mg/dl

}
