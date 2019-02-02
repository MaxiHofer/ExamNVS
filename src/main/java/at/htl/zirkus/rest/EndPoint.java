package at.htl.zirkus.rest;

import at.htl.zirkus.model.Act;
import at.htl.zirkus.model.ActMember;
import at.htl.zirkus.model.Program;
import at.htl.zirkus.model.Show;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/zirkus/rs")
@Stateless
public class EndPoint {

    @PersistenceContext
    EntityManager em;

    @GET
    @Path("/show/getShows")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Show> findAll() {
        TypedQuery<Show> query = em.createNamedQuery("Show.findAll", Show.class);
        return query.getResultList();
    }

    @POST
    @Path("/show/insertProgram")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProgram(Program program) {
        em.persist(program);
        return Response.ok().entity(program).build();
    }

    @PUT
    @Path("/act/updateAct/{name}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAct(@PathParam("name") String name, Act updatedAct) {
        if(updatedAct == null || em.find(Act.class, name) == null) {
            return Response.serverError().build();
        }
        updatedAct.setName(name);
        em.merge(updatedAct);
        return Response.ok().entity(em.find(Act.class, name)).build();
    }

    @DELETE
    @Path("act/act={aid}&member={mid}")
    public void deleteActMember(@PathParam("aid") long actId, @PathParam("mid") long memberId) {
        ActMember actMember = em.find(ActMember.class, memberId);
        Act act = em.find(Act.class, actId);
        if(actMember != null) {
            em.remove(em.contains(actMember) ? actMember : em.merge(actMember));
        }
        if(act != null) {
            em.remove(em.contains(act) ? act : em.merge(act));
        }
    }

}
