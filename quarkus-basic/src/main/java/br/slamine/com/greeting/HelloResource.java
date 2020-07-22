package br.slamine.com.greeting;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/hello")
@Traced
@Tag(name = "greeting")
public class HelloResource {

    @Inject
    GreetingService greetingService;

    @Inject
            @RestClient
    HelloClient helloClient;

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "say hello", description = "say hello to @config.user")
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
    @CircuitBreaker(delay = 10, delayUnit = ChronoUnit.SECONDS)
    public String remoteHello(){
       return helloClient.hello();
    }

    public String fallback(){
        return "I'm fallback";
    }
}