package com.microservice.patient.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.microservice.patient.service.PatientService;

@Component
//@EnableKafka
public class Consumer {

	@Autowired
	private PatientService patientService;
	
	Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	//@KafkaListener(topics="message_topic",groupId="msg_group")
	public void listenToTopic(String receivedMessage) {
		logger.info("The message recived is: {}",receivedMessage);
		String hospitalId = receivedMessage.substring(receivedMessage.indexOf("(") + 1, receivedMessage.indexOf(")"));
		patientService.deletePatientsBasedOnHospitalId(Long.parseLong(hospitalId));
		
	} 
	
}
