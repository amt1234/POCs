package com.microservice.patient.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.microservice.patient.entity.Patient;
import com.microservice.patient.exception.EmptyInputFieldException;
import com.microservice.patient.repository.PatientRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestPatientService {

	@MockBean
	private PatientRepository repoMock;

	@Autowired
	private PatientServiceImpl serviceMock;

	Patient patient1 = new Patient(1L, "Ridi", 27, "fever", 1L, "9999999977");
	Patient patient2 = new Patient(2L, "Priya", 32, "Astma", 1L, "9999966677");
	Patient patient3 = new Patient(3L, "Rakesh", 40, "Kidney stone", 2L, "8899966677");

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		MockMvcBuilders.standaloneSetup(serviceMock).build();
	}

	@Test
	void testSave() {
		Patient patient4 = new Patient();
		patient4.setPatientId(4L);
		patient4.setPatientName("Ravi");
		patient4.setPatientAge(50);
		patient4.setPatientDisease("leaver pain");
		patient4.setHospitalId(2L);
		patient4.setContactNumber("9933333445");

		Mockito.when(repoMock.saveAndFlush(patient4)).thenReturn(patient4);
		Patient savePatient = serviceMock.addPatient(patient4);
		assertThat(savePatient.getPatientId()).isEqualTo(patient4.getPatientId());
	}

	@Test
	void testSaveWithExceptionThrow() {
		Patient patient = new Patient();
		patient.setPatientId(1L);
		patient.setPatientName("");
		patient.setContactNumber("9999999666");
		patient.setPatientDisease("fever");
		patient.setHospitalId(null);

		// when - action or the behaviour that we are going test
		org.junit.jupiter.api.Assertions.assertThrows(EmptyInputFieldException.class, () -> {
			serviceMock.addPatient(patient);
		});

	}

	@Test
	void testFindAllPatients() {
		List<Patient> patientList = Arrays.asList(patient1, patient2, patient3);
		Mockito.when(repoMock.findAll()).thenReturn(patientList);
		Iterable<Patient> result = serviceMock.findAllPatients(1, 3);
		assertThat(result).hasSameSizeAs(patientList);
	}

	@Test
	void testFindPatientById() {
		Mockito.when(repoMock.findById(Mockito.anyLong())).thenReturn(Optional.of(patient1));
		Patient patientById = serviceMock.findPatientById(Mockito.anyLong());
		assertThat(patientById).isNotNull();
		assertEquals(patientById.getPatientId(), patient1.getPatientId());
	}

	@Test
	void testDeletePatientsBasedOnHospitalId() {
		Mockito.when(repoMock.findById(patient3.getHospitalId())).thenReturn(Optional.of(patient3));
		serviceMock.deletePatientsBasedOnHospitalId(patient3.getHospitalId());
		verify(repoMock).deleteByHospitalId(Mockito.anyLong());
	}

}
