package com.saber.spring_boot_web_demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data

public class JobDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
