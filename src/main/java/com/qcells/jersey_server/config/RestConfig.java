package com.qcells.jersey_server.config;

import com.qcells.jersey_server.rest.EmployeeResource;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;


@Component
public class RestConfig extends ResourceConfig {

    public RestConfig(){
        register(EmployeeResource.class);
    }
}