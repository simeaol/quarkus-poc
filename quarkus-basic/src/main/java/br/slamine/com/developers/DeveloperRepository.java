package br.slamine.com.developers;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class DeveloperRepository implements PanacheRepository<Developer> {
    public List<Developer> findDevs(String filter){
        return list("name like concat('%', ?1, '%')", filter);
    }
}
