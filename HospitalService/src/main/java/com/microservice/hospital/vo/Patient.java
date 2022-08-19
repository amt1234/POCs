package com.microservice.hospital.vo;

import java.beans.JavaBean;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JavaBean
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patientId;
	private String patientName;
	private int patientAge;
	private String patientDisease;
	private String contactNumber;
	private Long hospitalId;
	

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

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

	public Patient(Long patientId, String patientName, int patientAge, String patientDisease,Long hospitalId, String contactNumber) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientDisease = patientDisease;
		this.hospitalId = hospitalId;
		this.contactNumber = contactNumber;
	}

	public Patient() {
		
		// TODO Auto-generated constructor stub
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
