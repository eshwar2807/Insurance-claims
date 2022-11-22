package com.policy.process.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policy.process.model.PolicyData;

public interface PolicyDao extends JpaRepository<PolicyData, Long> {

	PolicyData findByPolicyIdAndPolicyHolderId(Long policyId, Long policyHolderId);

}
