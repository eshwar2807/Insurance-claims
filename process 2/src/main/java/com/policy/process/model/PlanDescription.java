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
@Table(name = "plan_description")
public class PlanDescription implements Serializable {

	private static final long serialVersionUID = 6297561573204950952L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column
	private String planId;

	@Column
	private String planName;

	@Column
	private String coverageType;

	@Column
	private String estimatedPremium;

	@Column
	private String annualDeductiableIndividual;

	@Column
	private String annualDeductiableFamily;

}
