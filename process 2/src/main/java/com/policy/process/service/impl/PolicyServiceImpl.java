package com.policy.process.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.policy.process.dao.PlanCoverageDao;
import com.policy.process.dao.PlanCoverageMappingDao;
import com.policy.process.dao.PlanDescriptionDao;
import com.policy.process.dao.PolicyDao;
import com.policy.process.dto.PolicyTransactionDto;
import com.policy.process.dto.PolicyTransactionProcessorResponse;
import com.policy.process.model.PlanCoverage;
import com.policy.process.model.PlanCoverageMapping;
import com.policy.process.model.PlanDescription;
import com.policy.process.model.PolicyData;
import com.policy.process.service.PolicyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

	private final PlanCoverageDao planCoverageDao;

	private final PlanCoverageMappingDao planCoverageMappingDao;

	private final PlanDescriptionDao planDescriptionDao;

	private final PolicyDao policyDao;

	@Override
	public List<PolicyTransactionProcessorResponse> processedTransaction(
			List<PolicyTransactionDto> policyTransactionDtoList) {
		List<PolicyTransactionProcessorResponse> processorResponseList = new ArrayList();
		Map<Long, List<PolicyTransactionDto>> policyWithPolicyHolder = policyTransactionDtoList.stream()
				.collect(Collectors.groupingBy(PolicyTransactionDto::getPolicyHolderId));
		for (Map.Entry<Long, List<PolicyTransactionDto>> entry : policyWithPolicyHolder.entrySet()) {
			for (PolicyTransactionDto policyTransactionDto : entry.getValue()) {
				PolicyTransactionProcessorResponse processorResponse = new PolicyTransactionProcessorResponse();
				PolicyData policy = policyDao.findByPolicyIdAndPolicyHolderId(policyTransactionDto.getPolicyId(),
						policyTransactionDto.getPolicyHolderId());
				if (Objects.isNull(policy)) {
					processorResponse.setErrorCode("E0001");
					processorResponse.setErrorMessage("Policy holder does not exists");
					processorResponse.setPolicyId(policyTransactionDto.getPolicyId());
					processorResponse.setPolicyHolderId(policyTransactionDto.getPolicyHolderId());
					processorResponse.setCoverageMainCategory(policyTransactionDto.getCoverageMainCategory());
					policyTransactionDto.setCoverageSubCategory(policyTransactionDto.getCoverageSubCategory());
					policyTransactionDto.setDateOfService(policyTransactionDto.getDateOfService());
				}
				if (Objects.nonNull(policy)) {
					PlanDescription planDescription = planDescriptionDao.findByPlanId(policy.getPlanId());
					PlanCoverage planCoverage = planCoverageDao
							.findByName(policyTransactionDto.getCoverageMainCategory());
					PlanCoverageMapping planMapping = planCoverageMappingDao.findByPlanCoverageIdAndSubCategory(
							planCoverage.getId(), policyTransactionDto.getCoverageSubCategory());
					processorResponse.setPlanId(planDescription.getPlanId());
					processorResponse.setRuleBased(planDescription.getPlanName());
					List<PolicyTransactionProcessorResponse> policyFilteredList = processorResponseList.stream()
							.filter(data -> data.getPolicyHolderId().equals(policyTransactionDto.getPolicyHolderId())
									&& planDescription.getPlanId().equals(data.getPlanId()))
							.collect(Collectors.toList());
					processorResponse.setRuleUsed(planMapping.getFirstPlanId());
					if (policyFilteredList.isEmpty()) {
						processorResponse.setPolicyHoderPays(policyTransactionDto.getBilledAmount());
						processorResponse.setIndividualAccumalated(policyTransactionDto.getBilledAmount());
						processorResponse.setFamilyAccumalated(policyTransactionDto.getBilledAmount());
						processorResponse.setProcessingMessage(
								"ANNUAL DECUCTIBLE  (INDIVIDUAL or FAMILY) not met, plan pays 0%");
					} else if (!policyFilteredList.isEmpty()) {

						double totalSum = policyFilteredList.stream()
								.mapToDouble(PolicyTransactionProcessorResponse::getBilledAmount).sum();
						Double doubleSum = new Double(totalSum);
						int sumValue = doubleSum.intValue();
						if (sumValue < Integer.valueOf(planDescription.getAnnualDeductiableIndividual())) {
							processorResponse.setPolicyHoderPays(policyTransactionDto.getBilledAmount());
							processorResponse
									.setIndividualAccumalated(totalSum + policyTransactionDto.getBilledAmount());
							processorResponse.setFamilyAccumalated(totalSum + policyTransactionDto.getBilledAmount());
							processorResponse.setProcessingMessage(
									"ANNUAL DECUCTIBLE  (INDIVIDUAL or FAMILY) not met, plan pays 0%");
						} else if (sumValue >= Integer.valueOf(planDescription.getAnnualDeductiableIndividual())
								&& sumValue < Integer.valueOf(planDescription.getAnnualDeductiableFamily())) {
							PolicyTransactionProcessorResponse latestData = policyFilteredList
									.get(policyFilteredList.size() - 1);
							Integer planPerventage = (((int) Integer.valueOf(planDescription.getPlanName()))
									* policyTransactionDto.getBilledAmount().intValue()) / 100;
							processorResponse.setPlanPays(String.valueOf(planPerventage));
							processorResponse
									.setPolicyHoderPays((policyTransactionDto.getBilledAmount() - planPerventage));
							processorResponse.setIndividualAccumalated(latestData.getIndividualAccumalated()
									+ (policyTransactionDto.getBilledAmount() - planPerventage));
							processorResponse.setFamilyAccumalated(latestData.getFamilyAccumalated()
									+ (policyTransactionDto.getBilledAmount() - planPerventage));
							processorResponse.setProcessingMessage(
									"ANNUAL DECUCTIBLE  (INDIVIDUAL) met, plan pays " + planDescription.getPlanName());

						} else if (sumValue > Integer.valueOf(planDescription.getAnnualDeductiableFamily())) {
							PolicyTransactionProcessorResponse latestData = policyFilteredList
									.get(policyFilteredList.size() - 1);
							Integer planPerventage = (((int) Integer.valueOf(planDescription.getPlanName()))
									* policyTransactionDto.getBilledAmount().intValue()) / 100;
							processorResponse.setPlanPays(String.valueOf(planPerventage));
							processorResponse
									.setPolicyHoderPays((policyTransactionDto.getBilledAmount() - planPerventage));
							processorResponse.setIndividualAccumalated(latestData.getIndividualAccumalated()
									+ (policyTransactionDto.getBilledAmount() - planPerventage));
							processorResponse.setFamilyAccumalated(latestData.getFamilyAccumalated()
									+ (policyTransactionDto.getBilledAmount() - planPerventage));
							processorResponse.setProcessingMessage(
									"ANNUAL DECUCTIBLE  (FAMILY) met, plan pays " + planDescription.getPlanName());
						}

					}
					processorResponse.setPolicyId(policyTransactionDto.getPolicyId());
					processorResponse.setPolicyHolderId(policyTransactionDto.getPolicyHolderId());
					processorResponse.setCoverageMainCategory(policyTransactionDto.getCoverageMainCategory());
					processorResponse.setCoverageSubCategory(policyTransactionDto.getCoverageSubCategory());

					processorResponse.setBilledAmount(policyTransactionDto.getBilledAmount());
					processorResponse.setDateOfService(policyTransactionDto.getDateOfService());

				}
				processorResponseList.add(processorResponse);
			}
		}
		return processorResponseList;
	}

}
