package api.parking.control.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import api.parking.control.controller.dto.ParkingCreateDTO;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest extends AbstractContainerBase{

	// mapemanto da porta aleatoria
	@LocalServerPort
	private int randomPort;

	// preparo do rest assure
	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	}

	@Test
	void whenFindAllThenCheckResult() {
		RestAssured.given()
			.when()
			.get("/parking")
			.then()
			.statusCode(HttpStatus.OK.value());
		// .body("license[0]", Matchers.equalTo("DMS-1111"));
		// extract().response().body().prettyPrint(); //aqui é pra imprimir
		// .statusCode(201) //verifica se o status code da requisição é 201, por exemplo
	}
	// extract().response().body().prettyPrint(); //aqui é pra imprimir
	// statusCode verifica se o status code da requisição é 201, por exemplo

	@Test
	void whenCreateThenCheckIsCreated() {
		var createDTO = new ParkingCreateDTO();
		createDTO.setColor("AMARELO");
		createDTO.setLicense("WRT-5555");
		createDTO.setModel("BRASILIA");
		createDTO.setState("SP");

		RestAssured.given()
			.when()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(createDTO).post("/parking").then()
			.statusCode(HttpStatus.CREATED.value())
			.body("license", Matchers.equalTo("WRT-5555"))
			.body("color", Matchers.equalTo("AMARELO"))
			.body("state", Matchers.equalTo("SP"));
	}
}
