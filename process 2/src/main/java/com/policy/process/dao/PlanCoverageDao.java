package com.policy.process.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.process.model.PlanCoverage;

public interface PlanCoverageDao extends JpaRepository<PlanCoverage, Long> {

	PlanCoverage findByName(String name);

}
