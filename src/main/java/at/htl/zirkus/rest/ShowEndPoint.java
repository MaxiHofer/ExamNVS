package at.htl.zirkus.rest;

import at.htl.zirkus.model.Show;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("show")
public class ShowEndPoint {
    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Show> getAll() {
        TypedQuery<Show> query = em.createNamedQuery("Show.findAll", Show.class);
        return query.getResultList();
    }

    @POST
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Show show) {
        em.persist(show);
        return Response.ok().entity(show).build();
    }
}
