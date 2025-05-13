package com.saber.spring_boot_web_demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title",length = 80)
    private String title;
    @Column(name = "description",length = 150)
    private String description;
    @Column(name = "location",length = 30)
    private String location;
    @Column(name = "minSalary")
    private BigDecimal minSalary;
    @Column(name = "maxSalary")
    private BigDecimal maxSalary;
}
