package br.slamine.com;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class GreetingService {

    @ConfigProperty(name="user.name")
    private  Optional<String> name;

    public Optional<String> userName(){
        return this.name;
    }
}
