package com.saber.spring_boot_web_demo.services.impl;

import com.saber.spring_boot_web_demo.dto.JobDto;
import com.saber.spring_boot_web_demo.dto.JobRequestDto;
import com.saber.spring_boot_web_demo.dto.JobResponseList;
import com.saber.spring_boot_web_demo.entities.Job;
import com.saber.spring_boot_web_demo.exceptions.BusinessException;
import com.saber.spring_boot_web_demo.exceptions.ResourceDuplicationException;
import com.saber.spring_boot_web_demo.exceptions.ResourceNotFoundException;
import com.saber.spring_boot_web_demo.mappers.JobMapper;
import com.saber.spring_boot_web_demo.repositories.JobRepository;
import com.saber.spring_boot_web_demo.services.JobServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobServicesImpl implements JobServices {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Override
    public JobResponseList getAllJobs() {
        return new JobResponseList(jobRepository.findAll());
    }

    @Override
    @Transactional
    public Long saveJob(JobRequestDto jobRequestDto) {
        Job job = jobMapper.dtoToModel(jobRequestDto);
        return jobRepository.save(job).getId();
    }

    @Override
    @Transactional
    public void updateJob(JobRequestDto jobRequestDto) {
        Job job = jobMapper.dtoToModel(jobRequestDto);
        jobRepository.save(job);
    }

    @Override
    public JobDto getJobById(Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isEmpty()) {
            String message = String.format("job with id %d does not exist", id);
            throw new ResourceNotFoundException(message);
        }
        Job job = jobOptional.get();
        return jobMapper.modelToDto(job);
    }

    @Override
    public void checkRulesJob(JobRequestDto jobRequestDto, Long currentJobId) {
        checkDuplicateJob(jobRequestDto.getTitle(), currentJobId);
        BigDecimal minSalary = jobRequestDto.getMinSalary();
        BigDecimal maxSalary = jobRequestDto.getMaxSalary();
        if (minSalary.compareTo(maxSalary) > 0) {
            throw new BusinessException((short) 7951, "maxSalary must be great than minSalary");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jobRepository.deleteById(id);
    }

    private void checkDuplicateJob(String title, Long currentJobId) {
        List<Job> jobs = jobRepository.findByTitle(title);
        if (currentJobId != null) {
            jobs = jobs.stream()
                    .filter(j -> !j.getId().equals(currentJobId))
                    .toList();
        }
        if (!jobs.isEmpty()) {
            throw new ResourceDuplicationException(String.format("job by title %s before save.impossible save job again"
                    , title));
        }

    }
}
