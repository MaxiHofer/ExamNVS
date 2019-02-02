package at.htl.zirkus.business;

import at.htl.zirkus.model.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class InitBean {

  @PersistenceContext
  EntityManager em;

  public InitBean() {
  }

  @Transactional
  private void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
    System.err.println("******** Init started");

    Act act = new Act("Clownact");
    List<ActMember> actMembers = new ArrayList<>();
    actMembers.add(new Actor(act, "Franz", "Huber", "123456789"));
    actMembers.add(new Animal(act, "Wendy", "Pferd"));

    act.setActMembers(actMembers);
    em.persist(act);
    actMembers.forEach(actMember -> em.persist(actMember));

    Show show = new Show("Franz der Clown", LocalDateTime.of(2019,1,5,11,11,0), LocalDateTime.of(2019,1,5,19,11,0));
    List<Act> acts = new ArrayList<>();
    acts.add(act);
    show.setActs(acts);
    em.persist(show);
  }

}