package api.parking.control.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import api.parking.control.controller.dto.ParkingCreateDTO;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("postgres")
public class ParkingControllerTest{

	@LocalServerPort
	private int randomPort;

	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	}

	@Test
	void whenFindAllThenCheckResult() {
		RestAssured.given()
		 	.auth().basic("user", "12345")
			.when()
			.get("/parking")
			.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	void whenCreateThenCheckIsCreated() {
		var createDTO = new ParkingCreateDTO();
		createDTO.setColor("PRATA");
		createDTO.setLicense("PLO-2H15");
		createDTO.setModel("SANDERO GTLINE");
		createDTO.setState("BAHIA");

		RestAssured.given()
			.when()
			.auth().basic("user", "12345")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(createDTO).post("/parking").then()
			.statusCode(HttpStatus.CREATED.value())
			.body("license", Matchers.equalTo("PLO-2H15"))
			.body("color", Matchers.equalTo("PRATA"))
			.body("model", Matchers.equalTo("SANDERO GTLINE"))
			.body("state", Matchers.equalTo("BAHIA"));
	}
}
