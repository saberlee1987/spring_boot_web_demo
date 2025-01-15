package com.saber.spring_boot_web_demo.services;

import com.saber.spring_boot_web_demo.dto.JobDto;
import com.saber.spring_boot_web_demo.dto.JobRequestDto;
import com.saber.spring_boot_web_demo.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobFacade {
    private final JobServices jobServices;

    public JobDto saveJob(JobRequestDto jobRequestDto) {
        jobRequestDto.setId(null);
        jobServices.checkRulesJob(jobRequestDto, null);
        Long jobId = jobServices.saveJob(jobRequestDto);
        return jobServices.getJobById(jobId);
    }

    public JobDto updateJob(JobRequestDto jobRequestDto) {
        if (jobRequestDto.getId() == null) {
            throw new BusinessException((short) 7952,
                    "id job for update is required");
        }
        jobServices.getJobById(jobRequestDto.getId());
        jobServices.checkRulesJob(jobRequestDto, jobRequestDto.getId());
        Long jobId = jobServices.saveJob(jobRequestDto);
        return jobServices.getJobById(jobId);
    }


    public String deleteJobById(Long id){
        jobServices.getJobById(id);
        jobServices.deleteById(id);
        return "job deleted successfully";
    }

}
