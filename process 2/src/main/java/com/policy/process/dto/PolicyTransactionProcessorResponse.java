package com.policy.process.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PolicyTransactionProcessorResponse {

	private Long policyId;

	private Long policyHolderId;

	private String dateOfService;

	private String coverageMainCategory;

	private String coverageSubCategory;

	private Double billedAmount;

	private Double policyHoderPays;

	private String planPays;

	private String ruleUsed;

	private Double individualAccumalated;

	private Double familyAccumalated;

	private String errorCode;

	private String errorMessage;

	private String processingMessage;

	@JsonIgnore
	private String planId;

	@JsonIgnore
	private String ruleBased;

}
