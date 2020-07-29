package com.qcells.jersey_server.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.qcells.jersey_server.model.Employee;
import com.qcells.jersey_server.modules.DataGenerate;
import com.qcells.jersey_server.repository.EmployeeRepository;
@Configuration
@PropertySource("application.properties")
@Path("/employees")
public class EmployeeResource {

    @Autowired
    private EmployeeRepository employeeRepository;

    private DataGenerate data_gen=new DataGenerate();

    @GET
    @Path("/hello/{id}")
    @Produces( MediaType.APPLICATION_JSON )
    public Employee get(@PathParam("id") int id) {
        return employeeRepository.getEmployee(id);
    }
    @GET
    @Path("/hello")
    @Produces({MediaType.APPLICATION_JSON})
    public String helloworld(){
        return "helloworld";
    }
    
    @GET
    @Path("/hello/data")
    @Produces({MediaType.APPLICATION_JSON})
    public JSONObject data_gen_json(){
        return  data_gen.DataGenerate_toJson();
    }

    @Value("${app.message}")
    private String message;
    @GET
    @Path("/prop")
    public String prop(){
        System.out.println("PROP:"+message);
        return message;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Employee getEmployee(@PathParam("id") int id) {
        return employeeRepository.getEmployee(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateEmployee(Employee employee, @PathParam("id") int id) {
        employeeRepository.updateEmployee(employee, id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteEmployee(@PathParam("id") int id) {
        employeeRepository.deleteEmployee(id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addEmployee(Employee employee, @Context UriInfo uriInfo) {
        employeeRepository.addEmployee(new Employee(employee.getId(), employee.getFirstName()));
        return Response.status(Response.Status.CREATED.getStatusCode()).header("Location", String.format("%s/%s", uriInfo.getAbsolutePath().toString(), employee.getId())).build();
    }
}