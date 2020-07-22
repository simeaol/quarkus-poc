package br.slamine.com.developers;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Entity;

@Entity
public class Developer extends PanacheEntity {

    @Schema(name = "name", title = "dev name", required = true)
    public String name;

    @Schema(hidden = true, minLength = 2, minimum = "2", maximum = "10")
    String password;
}
