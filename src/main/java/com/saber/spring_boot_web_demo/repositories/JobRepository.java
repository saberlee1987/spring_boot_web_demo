package com.saber.spring_boot_web_demo.repositories;

import com.saber.spring_boot_web_demo.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByTitle(String title);
}
