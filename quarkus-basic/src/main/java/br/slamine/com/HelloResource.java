package br.slamine.com;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/hello")
public class HelloResource {

    @Inject
    private GreetingService greetingService;

    @Inject
    @RestClient
    private HelloClient helloClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello " + greetingService
                .userName()
                .orElse("unknown");
    }

    @GET
    @Path("/async")
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<String> async(){
        return CompletableFuture.supplyAsync(() -> "I'm async");
    }

    @Fallback(fallbackMethod = "fallback")
    @GET
    @Path("/remote")
    @Produces(MediaType.TEXT_PLAIN)
    public String remoteHello(){
       return helloClient.hello();
    }

    public String fallback(){
        return "I'm fallback";
    }
}