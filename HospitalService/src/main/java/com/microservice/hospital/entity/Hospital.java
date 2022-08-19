package com.microservice.hospital.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long hospitalId;
	private String hospitalName;
	private String hospitalAddress;

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public Hospital(Long hospitalId, String hospitalName, String hospitalAddress) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.hospitalAddress = hospitalAddress;
	}

	public Hospital() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(hospitalAddress, hospitalId, hospitalName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hospital other = (Hospital) obj;
		return Objects.equals(hospitalAddress, other.hospitalAddress) && Objects.equals(hospitalId, other.hospitalId)
				&& Objects.equals(hospitalName, other.hospitalName);
	}

}
