package com.practice.carfactory;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.GregorianCalendar;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CarFactoryApplicationTests {
//Car Integration Test


	@Test
	void getCar() {
		get("/cars/200").
				then().
				contentType(JSON).
				body("make", equalTo("Bentley"));
	}

	@Test
	void getAllCars() {
		get("/cars").
				then().
				contentType(JSON).
				assertThat().
				body("size()", is(7));
	}

	@Test
	void createCar() {
		RequestSpecification request = given();
		request.header("content-type", MediaType.APPLICATION_JSON_VALUE);
		request.body(new Car(23l,"Chevy", "Tahoe", 2016));
		Response response = request.post("/cars").andReturn();
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
		String location = response.getHeader("location");
		assertTrue(location.endsWith("/cars/23"));
	}

	@Test
	void deleteCar() {
		delete("cars/2170").
				then().
				statusCode(204);

	}
}
