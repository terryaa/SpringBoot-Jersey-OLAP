package com.qcells.jersey_server.client;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



import com.qcells.jersey_server.model.Employee;

public class RestClient {

    private static final String REST_URI = "http://172.29.215.113:8080/employees";
    private Client client = ClientBuilder.newClient();

    public Response createJsonEmployee(Employee emp) {
        return client.target(REST_URI).request(MediaType.APPLICATION_JSON).post(Entity.entity(emp, MediaType.APPLICATION_JSON));
    }

    public Employee getJsonEmployee(int id) {
        return client.target(REST_URI).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get(Employee.class);
    }

    public Response createXmlEmployee(Employee emp) {
        return client.target(REST_URI).request(MediaType.APPLICATION_XML).post(Entity.entity(emp, MediaType.APPLICATION_XML));
    }
    public Employee getXmlEmployee(int id) {
        return client.target(REST_URI).path(String.valueOf(id)).request(MediaType.APPLICATION_XML).get(Employee.class);
    }
    //Detailed. Not Flowing version
    public Employee getXmlEmployee2(int id) {
        WebTarget target=client.target(REST_URI);
        WebTarget employeeTarget=target.path("1");
        Invocation.Builder invocation=employeeTarget.request(MediaType.APPLICATION_XML);
        //Response를 가져오는 방법
        //Response res1=invocation.get();
        Employee res=invocation.get(Employee.class);
        return res;
    }
}