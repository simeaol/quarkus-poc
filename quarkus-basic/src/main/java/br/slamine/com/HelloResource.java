package br.slamine.com;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/hello")
public class HelloResource {

    @Inject
    private GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello " + greetingService
                .userName()
                .orElse("unknown");
    }

    @GET()
    @Path("/async")
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<String> async(){
        return CompletableFuture.supplyAsync(() -> "I'm async");
    }
}