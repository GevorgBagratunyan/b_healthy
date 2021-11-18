package com.blueteam.history.entity.history.exam;

import com.blueteam.history.entity.history.History;
import com.blueteam.history.entity.history.exam.blood.BloodAnalize;
import com.blueteam.history.entity.history.exam.radiology.Radiology;

import javax.persistence.*;

@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "blood_analize_id")
    private BloodAnalize blood;

    @OneToOne(mappedBy = "exam")
    private Radiology radiology;

    @OneToOne(mappedBy = "exam")
    private History history;
}
