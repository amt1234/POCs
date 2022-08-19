package com.microservice.hospital.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.hospital.entity.Hospital;
import com.microservice.hospital.service.HospitalService;
import com.microservice.hospital.vo.ResponseTemplateVO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/hospital")
@CrossOrigin(origins = "http://localhost:3000")
public class HospitalController {

	Logger logger = LoggerFactory.getLogger(HospitalController.class);
	@Autowired
	HospitalService hospitalService;

	@PostMapping("/add")
	public ResponseEntity<Hospital> addHospital(@RequestBody Hospital hospital) {
		Hospital addedHospital = hospitalService.addHospital(hospital);
		return new ResponseEntity<Hospital>(addedHospital, HttpStatus.CREATED);
	}

	@GetMapping("id/{id}")
	public ResponseEntity<Hospital> getByHospitalId(@PathVariable Long id) {
		Hospital hospital = hospitalService.findByHospitalId(id);
		return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
	}

	@GetMapping("/names")
	public ResponseEntity<List<String>> getAllHospitalsName() {
		List<String> hospitalNames = hospitalService.getAllHospitalsName();
		return new ResponseEntity<List<String>>(hospitalNames, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteHospital(@PathVariable Long id){
		hospitalService.deleteByHospitalId(id);
		return new ResponseEntity<String>("Hospital deleted sucessfully!", HttpStatus.OK);
	}
	
	@CircuitBreaker(name="hospital-service", fallbackMethod = "getAllDataOfHosbitalByIdFallback")
	@GetMapping("/hospitalDetails/{id}")
	public ResponseEntity<ResponseTemplateVO> getAllDataOfHosbitalById(@PathVariable Long id) {
		return new ResponseEntity<ResponseTemplateVO>(hospitalService.getHospitalDetails(id), HttpStatus.OK);
	}
	
	public ResponseEntity<Hospital> getAllDataOfHosbitalByIdFallback(@PathVariable Long id,Throwable throwable){
		Hospital hospital = hospitalService.findByHospitalId(id);
		logger.warn("Patient service is down");
		return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
	}
}
