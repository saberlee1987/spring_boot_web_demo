package com.saber.spring_boot_web_demo.dto;

import com.saber.spring_boot_web_demo.entities.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponseList {
    private List<Job> jobs;
}
