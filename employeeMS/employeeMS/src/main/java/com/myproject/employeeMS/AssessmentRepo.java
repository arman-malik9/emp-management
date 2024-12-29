package com.myproject.employeeMS;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AssessmentRepo extends JpaRepository<Assessments, Long> {
	
	Optional<Assessments> findByUserId(Long userId);
//	Optional<Assessments> findByAssessment(String assessment);
}
