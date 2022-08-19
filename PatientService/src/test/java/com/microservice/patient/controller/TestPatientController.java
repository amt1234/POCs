package com.microservice.patient.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.microservice.patient.dto.PatientDTO;
import com.microservice.patient.entity.Patient;
import com.microservice.patient.service.PatientService;

@SpringBootTest
@AutoConfigureMockMvc
class TestPatientController {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();

	@MockBean
	private PatientService patientService;

	@InjectMocks
	private PatientController patientController;

	Patient patient1 = new Patient(1L, "Ridi", 27, "fever", 1L, "9999999977");
	Patient patient2 = new Patient(2L, "Priya", 32, "Astma", 1L, "9999966677");
	Patient patient3 = new Patient(3L, "Rakesh", 40, "Kidney stone", 2L, "8899966677");

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
	}

	@Test
	void testGetAllPatients() throws Exception {
		List<Patient> listOfPatient = new ArrayList<>(Arrays.asList(patient1, patient2, patient3));
		Mockito.when(patientService.findAllPatients(1,3)).thenReturn(listOfPatient);
		mockMvc.perform(MockMvcRequestBuilders.get("/patient/all/1/3").param("pageNumber", "1").param("pageLimit","3").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(listOfPatient.size())));

	}

	@Test
	void testGetPatientById() throws Exception {
		Mockito.when(patientService.findPatientById(Mockito.anyLong())).thenReturn(patient1);

		mockMvc.perform(MockMvcRequestBuilders.get("/patient/id/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.patientName", is(patient1.getPatientName())))
				.andExpect(jsonPath("$.patientAge", is(patient1.getPatientAge())));

	}

	@Test
	void testAddPatient() throws Exception {
		PatientDTO dto = new PatientDTO(4L, "Ravi", 2, "Kidney stone", "9933333445", 2L);

		Patient patient4 = new Patient();
		patient4.setPatientId(dto.getPatientId());
		patient4.setPatientName(dto.getPatientName());
		patient4.setPatientAge(dto.getPatientAge());
		patient4.setPatientDisease(dto.getPatientDisease());
		patient4.setHospitalId(dto.getHospitalId());
		patient4.setContactNumber(dto.getContactNumber());

		Mockito.when(patientService.addPatient(Mockito.any(Patient.class))).thenReturn(patient4);

		String content = objectWriter.writeValueAsString(patient4);

		MockHttpServletRequestBuilder mockRquest = MockMvcRequestBuilders.post("/patient/add")
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
				.content(content);

		mockMvc.perform(mockRquest).andExpect(status().isCreated()).andDo(print())
				.andExpect(jsonPath("$.patientName", is(patient4.getPatientName())))
				.andExpect(jsonPath("$.patientAge", is(patient4.getPatientAge())));

	}

	@Test
	void testDeletePatientsBasedOnHospitalId() throws Exception {
		doNothing().when(patientService).deletePatientsBasedOnHospitalId(Mockito.anyLong());

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/patient/deleteByHospitalId/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testGetPatientsByHospitalId() throws Exception {
		List<Patient> listOfPatient = new ArrayList<>(Arrays.asList(patient1, patient2));
		Mockito.when(patientService.findByHospitalId(Mockito.anyLong())).thenReturn(listOfPatient);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/patient/findByHospitalId/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[1].patientName", is("Priya")));
	}


}
