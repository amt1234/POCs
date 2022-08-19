package com.microservice.patient.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.patient.entity.Patient;

@Service
public interface PatientService {

	public Patient addPatient(Patient patient);

	public Patient findPatientById(Long id);
	
	public List<Patient> findByHospitalId(Long id);
	
	public void deletePatientsBasedOnHospitalId(Long hospitalId);

	List<Patient> findAllPatients(Integer pageNumber, Integer pageLimit);
}
