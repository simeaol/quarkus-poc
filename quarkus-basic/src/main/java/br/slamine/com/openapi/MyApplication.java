package br.slamine.com.openapi;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(
        description = "Quarkus POC - Proof of Concept. Supersonic subatomic java framework." ,
        title = "Rest api doc",
        version = "0.0.1",
        contact = @Contact(
                name = "slamine",
                email = "simeaolm@gmail.com",
                url = "https://simeao.com.br")))
public class MyApplication extends Application {
}
