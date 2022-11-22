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
@Table(name = "policy_data")
public class PolicyData implements Serializable {

	private static final long serialVersionUID = -8697760731954491062L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long policyId;

	@Column
	private Long policyHolderId;

	@Column
	private String planId;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String coverageStartDate;

	@Column
	private String coverageEndDate;

	@Column
	private float accumulatedDeductiable;

	@Column
	private String deductiable;

}
