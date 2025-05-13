package com.saber.spring_boot_web_demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data

public class JobRequestDto {
    private Long id;
    @NotBlank(message = "title is required")
    @Size(max =80 , message = "title maximum length is 80")
    private String title;
    @Size(max =150 , message = "description maximum length is 150")
    private String description;
    @NotBlank(message = "location is required")
    @Size(max =30 , message = "location maximum length is 30")
    private String location;
    @NotNull(message = "minSalary is required")
    @Positive(message = "minSalary must be positive")
    @Digits(integer = 19,fraction = 2,message = "minSalary maximum is 19 digits")
    private BigDecimal minSalary;
    @NotNull(message = "maxSalary is required")
    @Positive(message = "maxSalary must be positive")
    @Digits(integer = 19,fraction = 2,message = "maxSalary maximum is 19 digits")
    private BigDecimal maxSalary;
}
