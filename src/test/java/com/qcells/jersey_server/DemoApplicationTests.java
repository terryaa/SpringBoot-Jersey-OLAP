package com.qcells.jersey_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.qcells.jersey_server.client.RestClient;
import com.qcells.jersey_server.client.Rx_Client;
import com.qcells.jersey_server.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import org.junit.runner.RunWith;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

	public static final int HTTP_CREATED = 201;
	private RestClient client = new RestClient();
	private Rx_Client rx_client=new Rx_Client();
    @Autowired
	private TestRestTemplate restTemplate;
	
	//Simple String response Test
    @Test
    public void hello() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/employees/hello",
                String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("HELLOWORLD");
	}
	// Read application.properties file.
	@Test
	public void prop(){
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/employees/prop",
		String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("Hello prop!");
	}

	//get Employee Info by ID
	@Test
	public void getEmpl(){
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/employees/1",
		String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("Jane");
	}

	//Client Create Test (Fail if client exists)
	@Test
	public void create_client(){
		Employee emp = new Employee(7, "TT");
        Response response = client.createJsonEmployee(emp);
        assertThat(response.getStatus()).isEqualTo(HTTP_CREATED);
	}

	//NotFound Page Test
    @Test
    public void validation() {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/val_test_url",
                String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	//Getting Employee info by Json file. 
	@Test
    public void getEmployinfo_byJson() {
        int employeeId = 1;
        Employee emp = client.getJsonEmployee(employeeId);
        assertEquals(emp.getFirstName(), "Jane");
	}

	//Getting Employees info by XML 
	//07.29 still showing 500
	@Test
    public void getEmployinfo_byXML() {
		int employeeId = 1;
		Employee emp = client.getXmlEmployee2(employeeId);

        assertEquals(emp.getFirstName(), "Jane");
	}
	//Create Employees with XML
	//07.29 still showing 500
	@Test
    public void givenCorrectObject_whenCorrectXmlRequest_thenResponseCodeCreated() {
        Employee emp = new Employee(10, "Jacky");

        Response response = client.createXmlEmployee(emp);

        assertEquals(response.getStatus(), HTTP_CREATED);
	}

	//rx_client getAllEmployees' IDs test 
	@Test
    public void getEmployinfo_RXlist() throws InterruptedException {
		List<String> received = rx_client.sendData();

		//All memebers of Employees
		List<String> expected=new ArrayList<>();
		expected.add("Jane");
		expected.add("Jack");
		expected.add("George");

		//Compare.
        assertThat(received).containsAll(expected);
	}
}