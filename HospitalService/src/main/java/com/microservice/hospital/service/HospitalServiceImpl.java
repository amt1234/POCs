package com.microservice.hospital.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.microservice.hospital.entity.Hospital;
import com.microservice.hospital.kafka.Producer;
import com.microservice.hospital.repository.HospitalRepository;
import com.microservice.hospital.vo.Patient;
import com.microservice.hospital.vo.ResponseTemplateVO;

@Component
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	HospitalRepository hospitalRepository;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Producer producer;

	final static String HOSPITAL_DELETED_MSG = "Hospital Has been deleted, Please delete all patients for that particluar hospital.";

	@Override
	public Hospital addHospital(Hospital hospital) {
		return hospitalRepository.save(hospital);
	}

	@Override
	public Hospital findByHospitalId(Long id) {
		Hospital hospital;
		if (id == null) {
			throw new IllegalArgumentException();
		}
		try {
			hospital = hospitalRepository.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new NoSuchElementException();
		}
		return hospital;
	}

	@Override
	public void deleteByHospitalId(Long id) {
		hospitalRepository.deleteById(id);
		producer.sendMsgToTopic(HOSPITAL_DELETED_MSG + "(" + id + ")");
	}

	@Override
	public List<String> getAllHospitalsName() {
		List<Hospital> hospitals = hospitalRepository.findAll();
		List<String> hospitalsName = hospitals.stream().map(hospital -> hospital.getHospitalName())
				.collect(Collectors.toList());
		return hospitalsName;
	}

	@SuppressWarnings("unchecked")
	public ResponseTemplateVO getHospitalDetails(Long hospitalId) {
		ResponseTemplateVO vo = new ResponseTemplateVO();

		// 1st get hospital details
		Hospital hospital = null;
		try {
			hospital = findByHospitalId(hospitalId);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException();
		}
		vo.setHospital(hospital);

		// 2nd get all patients register to that hospital
		List<Patient> listOfPatiens = null;
		try {
			listOfPatiens = restTemplate.getForObject("http://PATIENT-SERVICE/patient/findByHospitalId/" + hospitalId,
					List.class);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException();
		}

		vo.setPatients(listOfPatiens);
		return vo;

	}

}
