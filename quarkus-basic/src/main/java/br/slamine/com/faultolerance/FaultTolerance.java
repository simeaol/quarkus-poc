package br.slamine.com.faultolerance;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Path("/fault")
public class FaultTolerance {

    @GET
    @Path("/timeout")
    @Timeout(value = 10, unit = ChronoUnit.MILLIS)//Should be used for client and server requests
    public String timeout() throws InterruptedException {
        Thread.sleep(1000);
        return "Timout...";
    }

    @GET
    @Path("/retry")
    @Retry(
            maxRetries = 2, //max attempts per request
            abortOn = IllegalArgumentException.class,//about retries on IllegalArgumentException
            delay = 10,//retry after the specific delay in delayUnit
            delayUnit = ChronoUnit.SECONDS,
            retryOn = NullPointerException.class)//retry only on NullPointerException
    public String retry() throws Exception {
        if(new Random().nextBoolean()){
            throw new Exception("Error in retry");
        }
        return "Retry...";
    }

    @GET
    @Path("/circuit-breaker")
    @CircuitBreaker(
            requestVolumeThreshold = 20, //circuit target to open
            failureRatio = .5, //open circuit in case of 50% of 20 request failures and does not execute the method anymore
            delay = 10, //on failureRatio open circuit for 10 seconds before new attempts
            delayUnit = ChronoUnit.SECONDS,
            successThreshold = 5, //<on semi-open>how many successful attempts should be necessary to close the circuit
            skipOn = NullPointerException.class

    ) //state: closed(everything working well), open(something wrong happened), semi-open(how many attempts should be made to certify the service is available)
    public String circuitBreaker() throws Exception {
        if(new Random().nextInt() > 2){
            throw new Exception("Exception occurred. Circuit breaker raised.");
        }
        return "Circuit Breaker...";
    }

    @GET
    @Path("/fallback")
    @Fallback(fallbackMethod = "iamFallback")
    public String fallback() throws Exception {
        if(new Random().nextBoolean()){
            throw new Exception("Circuit open... returns Fallback");
        }
        return "Fallback...";
    }

    private String iamFallback(){
        return "I'm fallback";
    }
}
