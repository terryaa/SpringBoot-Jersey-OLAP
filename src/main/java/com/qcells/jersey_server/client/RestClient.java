package com.qcells.jersey_server.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.qcells.jersey_server.model.Employee;

public class RestClient {

    private static final String REST_URI = "http://172.29.215.113:8080/employees";
    private Client client = ClientBuilder.newClient();

    public Response createJsonEmployee(Employee emp) {
        Response retest = client.target(REST_URI).request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(emp, MediaType.APPLICATION_JSON));
        return retest;
    }

    public Employee getJsonEmployee(int id) {
        return client.target(REST_URI).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get(Employee.class);
    }

    public Response createXmlEmployee(Employee emp) {
        return client.target(REST_URI).request(MediaType.APPLICATION_XML)
                .post(Entity.entity(emp, MediaType.APPLICATION_XML));
    }

    public Employee getXmlEmployee(int id) {
        Builder str = client.target(REST_URI).path(String.valueOf(id)).request(MediaType.APPLICATION_XML);
        str.get(Employee.class);
        return client.target(REST_URI).path(String.valueOf(id)).request(MediaType.APPLICATION_XML).get(Employee.class);
    }
}