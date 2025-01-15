package com.saber.spring_boot_web_demo.services;

import com.saber.spring_boot_web_demo.dto.JobDto;
import com.saber.spring_boot_web_demo.dto.JobRequestDto;
import com.saber.spring_boot_web_demo.dto.JobResponseList;

public interface JobServices {

    JobResponseList getAllJobs();

    Long saveJob(JobRequestDto jobRequestDto);
    void updateJob(JobRequestDto jobRequestDto);

    JobDto getJobById(Long id);

    void checkRulesJob(JobRequestDto jobRequestDto,Long currentJobId);

    void deleteById(Long id);
}
