package com.microservice.hospital.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.hospital.entity.Hospital;
import com.microservice.hospital.vo.ResponseTemplateVO;

@Service
public interface HospitalService {

	public Hospital addHospital(Hospital hospital);

	public Hospital findByHospitalId(Long id);

	public void deleteByHospitalId(Long id);
	
	public List<String> getAllHospitalsName();
	
	public ResponseTemplateVO getHospitalDetails(Long id);
}
