package com.blueteam.history.entity.history.exam.blood;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "blood_general")
@Data
public class BloodGeneral {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "analyzed_date")
    private LocalDate analyzedDate;

    @Column(name = "m_rbc")
    private double mRedBloodCells;

    @Column(name = "f_rbc")
    private double fRedBloodCells;

    @Column(name = "wbc")
    private double whiteBloodCells;

    @Column(name = "m_hb")
    private double mHemoglobin;

    @Column(name = "f_hb")
    private double fHemoglobin;

    @Column(name = "m_esr")
    private double mEsr;

    @Column(name = "f_esr")
    private double fEsr;

}
