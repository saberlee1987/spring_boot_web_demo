package com.saber.spring_boot_web_demo.mappers;

import com.saber.spring_boot_web_demo.dto.JobDto;
import com.saber.spring_boot_web_demo.dto.JobRequestDto;
import com.saber.spring_boot_web_demo.entities.Job;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface JobMapper {

    Job dtoToModel(JobRequestDto jobRequestDto);

    Job dtoToModel(JobDto jobDto);

    List<JobDto> modelToDto(List<Job> jobs);
    JobDto modelToDto(Job job);
}
