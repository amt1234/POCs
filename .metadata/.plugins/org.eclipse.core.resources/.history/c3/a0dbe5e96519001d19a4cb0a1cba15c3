package com.microservice.patient.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.patient.entity.Patient;
import com.microservice.patient.exception.EmptyInputFieldException;
import com.microservice.patient.repository.PatientRepository;

@Component
public class PatientServiceImpl implements PatientService {

	Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Patient addPatient(Patient patient) {
		Patient addedPatient = null;
		if (patient != null && (patient.getPatientName().isEmpty() || patient.getHospitalId() == null
				|| patient.getPatientDisease().isEmpty() || patient.getContactNumber().isEmpty())) {
			throw new EmptyInputFieldException("Input Field is empty.", "601");
		}
		addedPatient = patientRepository.saveAndFlush(patient);
		return addedPatient;
	}

	@Override
	public Patient findPatientById(Long id) {
		Patient patient = null;
		try {
			Optional<Patient> optional = patientRepository.findById(id);
			if (optional.isPresent()) {
				patient = optional.get();
			}
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException();
		}

		return patient;
	}

	@Override
	public List<Patient> findAllPatients(Integer pageNumber, Integer pageLimit) {
		return patientRepository.getAllPatients(pageNumber,pageLimit);
	}

	@Override
	public List<Patient> findByHospitalId(Long hospitalId) {
		List<Patient> patients;
		patients = patientRepository.findByHospitalId(hospitalId);
		return patients;
	}

	@Transactional
	public void deletePatientsBasedOnHospitalId(Long hospitalId) {
		List<Patient> patients;
		if (hospitalId == null) {
			throw new IllegalArgumentException();
		}
		patients = patientRepository.deleteByHospitalId(hospitalId);
		logger.info("Patients deleted sucessfully count : {}", patients.size());
	}

}
