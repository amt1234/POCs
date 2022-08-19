package com.microservice.hospital.vo;

import java.beans.JavaBean;
import java.util.List;
import java.util.Objects;

import com.microservice.hospital.entity.Hospital;

@JavaBean
public class ResponseTemplateVO {

	private Hospital hospital;
	private List<Patient> patients;

	public Hospital getHospital() {	
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}


}
