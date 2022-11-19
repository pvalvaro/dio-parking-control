package api.parking.control.service;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import api.parking.control.model.Parking;
import api.parking.control.services.ParkingCheckOut;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("postgres")
public class ParkingCheckOutTest{

	@LocalServerPort
	private int randomPort;

	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	}
	
	@Test
	void whenCheckOutThenGetBill() {
		var parking = new Parking();
		parking.setEntryDate(LocalDateTime.of(2022, Month.NOVEMBER, 18, 11, 30, 40));
		parking.setExitDate(LocalDateTime.now());
		parking.setBill(ParkingCheckOut.getBill(parking));
		
		RestAssured.given()
		.when()
		.auth().basic("user", "12345")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(parking)
		.post("/parking")
		.then()
		.statusCode(HttpStatus.CREATED.value());
	}
}
