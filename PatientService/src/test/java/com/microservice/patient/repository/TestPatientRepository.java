package com.microservice.patient.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.microservice.patient.entity.Patient;

@SpringBootTest
@AutoConfigureMockMvc
class TestPatientRepository {

	@MockBean
	private PatientRepository patientRepository;

	Patient patient1 = new Patient(1L, "ABC", 27, "Leg Pain", 1L, "9999999977");
	Patient patient2 = new Patient(2L, "xyz", 30, "Fever", 1L, "9999889977");
	Patient patient3 = new Patient(3L, "pqz", 40, "Fever", 2L, "9977889977");

	@Test
	void findByHospitalIdTest() {
		List<Patient> tempPatientsList = Arrays.asList(patient1, patient2, patient3);
		when(patientRepository.findByHospitalId(Mockito.anyLong())).thenReturn(tempPatientsList);

		List<Patient> patients = patientRepository.findByHospitalId(Mockito.anyLong());
		assertThat(patients).isNotNull();
	}

	@Test
	void deleteByHospitalIdTest() {
		List<Patient> tempPatientsList = Arrays.asList(patient1, patient2, patient3);
		when(patientRepository.findByHospitalId(Mockito.anyLong())).thenReturn(tempPatientsList);

		List<Patient> patients = patientRepository.deleteByHospitalId(Mockito.anyLong());
		assertThat(patients).isNotNull();
	}

}
