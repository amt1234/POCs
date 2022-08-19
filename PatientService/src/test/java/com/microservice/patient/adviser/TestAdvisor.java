package com.microservice.patient.adviser;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.microservice.patient.controller.PatientController;
import com.microservice.patient.entity.Patient;
import com.microservice.patient.service.PatientService;

@SpringBootTest
@AutoConfigureMockMvc
class TestAdvisor {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();

	@MockBean
	private PatientService patientService;

	Patient patient1 = new Patient(1L, "Ridi", 27, "fever", 1L, "9999999977");
	Patient patient2 = new Patient(2L, "Priya", 32, "Astma", 1L, "9999966677");
	Patient patient3 = new Patient(3L, "Rakesh", 40, "Kidney stone", 2L, "8899966677");


	@Test
	public void testHandleHttpRequestMethodNotSupported() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/patient/id/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isMethodNotAllowed());

	}

	@Test
	public void testHandleNoSuchElementException() throws Exception {
		Long id = null;
		mockMvc.perform(MockMvcRequestBuilders.get("/hospital/id/" + id).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
}
