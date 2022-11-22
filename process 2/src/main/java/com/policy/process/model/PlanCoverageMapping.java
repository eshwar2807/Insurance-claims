package com.policy.process.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "plan_coverage_mapping")
public class PlanCoverageMapping implements Serializable {

	private static final long serialVersionUID = 8558268190109407849L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long planCoverageId;

	@Column
	private String subCategory;

	@Column
	private String firstPlanId;

	@Column
	private String secondPlanId;

	@Column
	private String thirdPlanId;

}
