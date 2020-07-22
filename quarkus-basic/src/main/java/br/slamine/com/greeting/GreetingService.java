package br.slamine.com.greeting;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
@Traced
public class GreetingService {

    @ConfigProperty(name="user.name", defaultValue ="unknown")
    private  Optional<String> name;

    public Optional<String> userName(){
        return this.name;
    }
}
