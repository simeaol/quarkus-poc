package br.slamine.com;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@RestClient
public interface HelloClient {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello();
}
