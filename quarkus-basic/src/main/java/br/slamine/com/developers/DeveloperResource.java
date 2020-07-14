package br.slamine.com.developers;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/developers")
public class DeveloperResource {

    @Inject
    private DeveloperRepository developerRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Developer> all (){
        return Developer.listAll();
    }

    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Developer> all (@QueryParam("filter") String filter){
        return developerRepository.findDevs(filter);
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Developer newDev (Developer developer){
        developer.id = null;
        developer.persist();
        return developer;
    }
}
