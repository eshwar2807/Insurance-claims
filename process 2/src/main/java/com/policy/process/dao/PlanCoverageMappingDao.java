package com.policy.process.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.process.model.PlanCoverageMapping;

public interface PlanCoverageMappingDao extends JpaRepository<PlanCoverageMapping, Long> {

	PlanCoverageMapping findByPlanCoverageIdAndSubCategory(Long planCoverageId, String subCategory);

}
