package at.htl.zirkus.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;

@Entity
@NamedQuery(name = "Act.findAll", query = "select a from Act a join fetch a.shows")
public class Act implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonbTransient
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ActMember> actMembers;

    @JsonbTransient
    @ManyToMany(mappedBy = "acts", fetch = FetchType.LAZY)
    private List<Show> shows;

    public Act() {
    }

    public Act(String name) {
        this.name = name;
    }

    public Act(String name, List<ActMember> actMembers) {
        this.name = name;
        this.actMembers = actMembers;
    }

    public void addActMember(ActMember actMember) {
        if(!actMembers.contains(actMember)) {
            actMembers.add(actMember);
        }
        if(actMember.getAct() != this) {
            actMember.setAct(this);
        }
    }

    public void removeActMember(ActMember actMember) {
        if(actMembers.contains(actMember)) {
            actMembers.remove(actMember);
        }
        if(actMember.getAct() == this) {
            actMember.setAct(null);
        }
    }

    public void addShow(Show show) {
        if(!shows.contains(show)) {
            shows.add(show);
        }
        if(!show.getActs().contains(this)) {
            show.addAct(this);
        }
    }

    public void removeShow(Show show) {
        if(shows.contains(show)) {
            shows.remove(show);
        }
        if(show.getActs().contains(this)) {
            show.removeAct(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ActMember> getActMembers() {
        return actMembers;
    }

    public void setActMembers(List<ActMember> actMembers) {
        this.actMembers = actMembers;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
