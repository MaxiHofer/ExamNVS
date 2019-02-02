package at.htl.zirkus.rest;

import at.htl.zirkus.model.Act;
import at.htl.zirkus.model.ActMember;
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

@Path("act")
@Stateless
public class ActEndPoint {

    @PersistenceContext
    EntityManager em;

    @PUT
    @Path("{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAct(@PathParam("id") long id, Act updatedAct) {
        if(updatedAct == null || em.find(Act.class, id) == null) {
            return Response.serverError().build();
        }
        updatedAct.setId(id);
        em.merge(updatedAct);
        return Response.ok().entity(em.find(Act.class, id)).build();
    }

    @DELETE
    @Transactional
    @Path("member?act={aid}&member={mid}")
    public void deleteActMember(@PathParam("aid") long actId, @PathParam("mid") long memberId) {
        ActMember actMember = em.find(ActMember.class, memberId);
        if(actMember != null) {
            em.remove(em.contains(actMember) ? actMember : em.merge(actMember));
        }
    }

}
