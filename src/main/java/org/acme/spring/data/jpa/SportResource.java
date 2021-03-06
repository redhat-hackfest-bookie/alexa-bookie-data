package org.acme.spring.data.jpa;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/sports")
public class SportResource {

    private final SportRepository sportRepository;

    public SportResource(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    @GET
    @Produces("application/json")
    public Iterable<Sport> findAll() {
        return sportRepository.findAll();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam long id) {
        sportRepository.deleteById(id);
    }

    @POST
    @Path("/name/{name}/hitCount/{count}")
    @Produces("application/json")
    public Sport create(@PathParam String name, @PathParam int count) {
        return sportRepository.save(new Sport(name, count));
    }

    @PUT
    @Path("/id/{id}/hitCount/{count}")
    @Produces("application/json")
    public Sport updateCount(@PathParam Long id, @PathParam int count) {
        Optional<Sport> optional = sportRepository.findById(id);
        if (optional.isPresent()) {
            Sport sport = optional.get();
            sport.setHitCount(count);
            return sportRepository.save(sport);
        }

        throw new IllegalArgumentException("No Sport with id " + id + " exists");
    }

    @GET
    @Path("/update/{sport}")
    public Response updateCount(@PathParam String sport){
        List<Sport> sports = sportRepository.findByName(sport);
        if(!sports.isEmpty()){
            Sport league = sports.get(0);
            league.setHitCount(league.getHitCount() + 1);
            sportRepository.save(league);
            return Response.ok().build();
        }

        return Response.ok().build();
    }

    @GET
    @Path("/sport/{sport}")
    @Produces("application/json")
    public List<Sport> findBySport(@PathParam String sport) {
        return sportRepository.findByName(sport);
    }
}
