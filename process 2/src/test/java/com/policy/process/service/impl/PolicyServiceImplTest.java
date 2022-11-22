//package com.policy.process.service.impl;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.policy.process.dao.PlanCoverageDao;
//import com.policy.process.dao.PlanCoverageMappingDao;
//import com.policy.process.dao.PlanDescriptionDao;
//import com.policy.process.dao.PolicyDao;
//import com.policy.process.dto.PolicyTransactionDto;
//import com.policy.process.dto.PolicyTransactionProcessorResponse;
//import com.policy.process.model.PlanCoverage;
//import com.policy.process.model.PlanCoverageMapping;
//import com.policy.process.model.PlanDescription;
//import com.policy.process.model.PolicyData;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//public class PolicyServiceImplTest {
//
//	@Mock
//	private PlanCoverageDao planCoverageDao;
//
//	@Mock
//	private PlanCoverageMappingDao planCoverageMappingDao;
//
//	@Mock
//	private PlanDescriptionDao planDescriptionDao;
//
//	@Mock
//	private PolicyDao policyDao;
//
//	private static final String DATE = "2022/03/03";
//	private static final String PLAN_ID_FIRST = "P001";
//	private static final String PLAN_ID_SECOND = "P002";
//	private static final String PLAN_ID_THIRD = "P003";
//	private static final Long ID = 1L;
//	private static final String COVERAGE_TYPE = "Family";
//	private static final String AMOUNT = "200";
//	private static final String PLAN_NAME = "40";
//	private static final String NAME = "Test";
//	private static final String ERROR_CODE = "E0001";
//	private static final String ERROR_MESSAGE = "Policy holder does not exists";
//
//	@InjectMocks
//	private PolicyServiceImpl policyServiceImpl;
//
//	private PolicyData getPolicyData() {
//		PolicyData policyData = new PolicyData();
//		policyData.setAccumulatedDeductiable(200.0f);
//		policyData.setCoverageEndDate(DATE);
//		policyData.setCoverageStartDate(DATE);
//		policyData.setDeductiable(AMOUNT);
//		policyData.setFirstName(NAME);
//		policyData.setId(ID);
//		policyData.setLastName(NAME);
//		policyData.setPlanId(PLAN_ID_FIRST);
//		policyData.setPolicyHolderId(ID);
//		policyData.setPolicyId(ID);
//		return policyData;
//	}
//
//	private PlanDescription getPlanDesc() {
//		PlanDescription planDesc = new PlanDescription();
//		planDesc.setAnnualDeductiableFamily(AMOUNT);
//		planDesc.setAnnualDeductiableIndividual(AMOUNT);
//		planDesc.setCoverageType(COVERAGE_TYPE);
//		planDesc.setEstimatedPremium(NAME);
//		planDesc.setId(String.valueOf(ID));
//		planDesc.setPlanId(PLAN_ID_FIRST);
//		planDesc.setPlanName(PLAN_NAME);
//		return planDesc;
//	}
//
//	private PlanCoverage getPlanCoverage() {
//		PlanCoverage planCoverage = new PlanCoverage();
//		planCoverage.setId(ID);
//		planCoverage.setName(NAME);
//		return planCoverage;
//	}
//
//	private PlanCoverageMapping getPlanCoverageMapping() {
//		PlanCoverageMapping planCoverageMapping = new PlanCoverageMapping();
//		planCoverageMapping.setFirstPlanId(PLAN_ID_FIRST);
//		planCoverageMapping.setId(ID);
//		planCoverageMapping.setPlanCoverageId(ID);
//		planCoverageMapping.setSecondPlanId(PLAN_ID_SECOND);
//		planCoverageMapping.setThirdPlanId(PLAN_ID_THIRD);
//		planCoverageMapping.setSubCategory(NAME);
//		return planCoverageMapping;
//	}
//
//	@Test
//	public void processedTransactionWithPolicyNotFound() {
//		List<PolicyTransactionDto> policyTrasnactionDtoList = new ArrayList();
//		policyTrasnactionDtoList.add(getPolicyTransactionDto());
//		Mockito.when(policyDao.findByPolicyIdAndPolicyHolderId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(null);
//		List<PolicyTransactionProcessorResponse> response = policyServiceImpl
//				.processedTransaction(policyTrasnactionDtoList);
//		Assert.assertNotNull(response);
//		PolicyTransactionProcessorResponse policyTrasanctionResponse = response.get(0);
//		assertEquals(ERROR_CODE, policyTrasanctionResponse.getErrorCode());
//		assertEquals(ERROR_MESSAGE, policyTrasanctionResponse.getErrorMessage());
//	}
//
//	@Test
//	public void processedTransaction_WithSuccessfulTransaction() {
//		List<PolicyTransactionDto> policyTrasnactionDtoList = new ArrayList();
//		policyTrasnactionDtoList.add(getPolicyTransactionDto());
//		policyTrasnactionDtoList.add(getPolicyTransactionDto());
//		Mockito.when(policyDao.findByPolicyIdAndPolicyHolderId(Mockito.anyLong(), Mockito.anyLong()))
//				.thenReturn(getPolicyData());
//		Mockito.when(planDescriptionDao.findByPlanId(Mockito.anyString())).thenReturn(getPlanDesc());
//		Mockito.when(planCoverageDao.findByName(Mockito.anyString())).thenReturn(getPlanCoverage());
//		Mockito.when(planCoverageMappingDao.findByPlanCoverageIdAndSubCategory(Mockito.anyLong(), Mockito.anyString()))
//				.thenReturn(getPlanCoverageMapping());
//		List<PolicyTransactionProcessorResponse> response = policyServiceImpl
//				.processedTransaction(policyTrasnactionDtoList);
//		Assert.assertNotNull(response);
//		PolicyTransactionProcessorResponse policyTrasanctionResponse = response.get(0);
//		assertNotEquals(ERROR_CODE, policyTrasanctionResponse.getErrorCode());
//		assertNotEquals(ERROR_MESSAGE, policyTrasanctionResponse.getErrorMessage());
//		assertEquals(100001L, policyTrasanctionResponse.getPolicyId());
//		assertEquals(1000011L, policyTrasanctionResponse.getPolicyHolderId());
//		assertEquals("13/7/2016", policyTrasanctionResponse.getDateOfService());
//		assertEquals(3000.0, policyTrasanctionResponse.getPolicyHoderPays());
//		assertEquals(3000.0, policyTrasanctionResponse.getBilledAmount());
//		assertEquals(PLAN_ID_FIRST, policyTrasanctionResponse.getRuleUsed());
//	}
//
//	private PolicyTransactionDto getPolicyTransactionDto() {
//		PolicyTransactionDto policyTrasnaction = new PolicyTransactionDto();
//		policyTrasnaction.setBilledAmount(3000.00);
//		policyTrasnaction.setCoverageMainCategory("Inpatient Hospital Care");
//		policyTrasnaction.setCoverageSubCategory("SURGERY");
//		policyTrasnaction.setDateOfService("13/7/2016");
//		policyTrasnaction.setPolicyHolderId(1000011L);
//		policyTrasnaction.setPolicyId(100001L);
//		return policyTrasnaction;
//	}
//
//}
