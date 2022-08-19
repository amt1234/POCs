package com.microservice.patient.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patientId;
	@NotBlank
	@Pattern(regexp = "[a-zA-Z]+\\.?")
	private String patientName;

	@NotNull
	@Min(1)
	@Max(150)
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

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Patient(Long patientId, String patientName, int patientAge, String patientDisease, Long hospitalId,
			String contactNumber) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientDisease = patientDisease;
		this.hospitalId = hospitalId;
		this.contactNumber = contactNumber;
	}

	public Patient() {

	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", patientAge=" + patientAge
				+ ", patientDisease=" + patientDisease + ", contactNumber=" + contactNumber + ", hospitalId="
				+ hospitalId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(contactNumber, hospitalId, patientAge, patientDisease, patientId, patientName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(contactNumber, other.contactNumber) && Objects.equals(hospitalId, other.hospitalId)
				&& patientAge == other.patientAge && Objects.equals(patientDisease, other.patientDisease)
				&& Objects.equals(patientId, other.patientId) && Objects.equals(patientName, other.patientName);
	}

}
