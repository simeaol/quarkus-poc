package br.slamine.com.healthcheck;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import java.lang.annotation.Annotation;

public class ReadnessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("application is ready")
                .state(true)
                .withData("ready", true)
                .up()
                .build();
    }
}
