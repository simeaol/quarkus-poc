package br.slamine.com.developers;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Developer extends PanacheEntity {

    public String name;
}
