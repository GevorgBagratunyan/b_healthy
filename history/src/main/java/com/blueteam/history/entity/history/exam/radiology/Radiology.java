package com.blueteam.history.entity.history.exam.radiology;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "radiology")
public class Radiology {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    @JoinColumn(name = "radilogy_id")
    private List<Kt> kt;

    @OneToMany
    @JoinColumn(name = "radilogy_id")
    private List<Mrt> mrt;

    @OneToMany
    @JoinColumn(name = "radilogy_id")
    private List<Ultrasound> ultrasound;

}
