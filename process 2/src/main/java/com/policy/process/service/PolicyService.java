package com.policy.process.service;

import java.util.List;

import com.policy.process.dto.PolicyTransactionDto;

public interface PolicyService {

	Object processedTransaction(List<PolicyTransactionDto> policyTransactionDto);

}
