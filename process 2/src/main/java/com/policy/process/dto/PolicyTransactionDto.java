package com.policy.process.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyTransactionDto {

	private Long policyId;

	private Long policyHolderId;

	private String dateOfService;

	private String coverageMainCategory;

	private String coverageSubCategory;

	private Double billedAmount;

}
