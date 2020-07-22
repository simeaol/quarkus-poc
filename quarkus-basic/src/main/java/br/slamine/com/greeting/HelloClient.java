package br.slamine.com.greeting;

import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Traced
@RegisterRestClient
public interface HelloClient {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @RestClient
    public String hello();
}
