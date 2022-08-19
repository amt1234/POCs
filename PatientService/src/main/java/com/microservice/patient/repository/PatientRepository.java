package com.microservice.patient.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.patient.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	public List<Patient> findByHospitalId(Long id);
	
	@Transactional
	public List<Patient> deleteByHospitalId(Long id);
	
	default List<Patient> getAllPatients(Integer pageNumber, Integer pageLimit){
        Pageable firstPageWithTwoElements = PageRequest.of(pageNumber, pageLimit);
        return this.findAll(firstPageWithTwoElements).get().collect(Collectors.toList());
    }
}
