package com.microservice.patient.dto;

import java.beans.JavaBean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JavaBean
public class PatientDTO {

	private Long patientId;
	@NotBlank
	@Pattern(regexp = "[a-zA-Z]+\\.?")
	private String patientName;

	@Min(value = 1, message = "Please enter valid age")
	@Max(value = 100, message = "Please enter valid age")
	private int patientAge;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z ]*$")
	private String patientDisease;

	@NotNull
	@Pattern(regexp = "^\\d{10}$", message = "Please enter valid contact number")
	private String contactNumber;

	@NotNull
	private Long hospitalId;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public String getPatientDisease() {
		return patientDisease;
	}

	public void setPatientDisease(String patientDisease) {
		this.patientDisease = patientDisease;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public PatientDTO(Long patientId, @NotBlank @Pattern(regexp = "[a-zA-Z]+\\.?") String patientName,
			@Min(value = 1, message = "Please enter valid age") @Max(value = 100, message = "Please enter valid age") int patientAge,
			@NotNull @Pattern(regexp = "^[a-zA-Z ]*$") String patientDisease,
			@NotNull @Pattern(regexp = "^\\d{10}$", message = "Please enter valid contact number") String contactNumber,
			@NotNull Long hospitalId) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientDisease = patientDisease;
		this.contactNumber = contactNumber;
		this.hospitalId = hospitalId;
	}

	public PatientDTO() {
		super();
	}

}
