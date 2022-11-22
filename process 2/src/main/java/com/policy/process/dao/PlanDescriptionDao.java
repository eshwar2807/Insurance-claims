package com.policy.process.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.process.model.PlanDescription;

public interface PlanDescriptionDao extends JpaRepository<PlanDescription, Long> {

	PlanDescription findByPlanId(String planId);

}
