package br.slamine.com.healthcheck;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Gauge;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/envs")
public class ResourceCheck {

    @GET
    @Path("/free-memory")
    @Gauge(name = "Amount of free memory", unit = MetricUnits.BYTES)
    public long memory(){
        return Runtime.getRuntime().freeMemory();
    }
}
