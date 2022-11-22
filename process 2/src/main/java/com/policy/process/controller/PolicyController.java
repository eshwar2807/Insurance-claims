package com.policy.process.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policy.process.dto.PolicyTransactionDto;
import com.policy.process.service.PolicyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/policy")
public class PolicyController {

	private final PolicyService policyService;

	@PostMapping
	public ResponseEntity<Object> processedTransaction(@RequestBody List<PolicyTransactionDto> policyTransactionDto) {

		return ResponseEntity.ok(policyService.processedTransaction(policyTransactionDto));
	}

}
