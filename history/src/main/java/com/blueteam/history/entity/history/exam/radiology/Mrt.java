package com.blueteam.history.entity.history.exam.radiology;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "mrt")
@Data
public class Mrt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name= "coclusion")
    private String cunclusion;

    @Column(name= "date")
    private LocalDate mrtDate;

}
