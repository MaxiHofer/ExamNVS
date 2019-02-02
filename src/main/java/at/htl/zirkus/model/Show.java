package at.htl.zirkus.model;

import at.htl.zirkus.LocalDateTimeAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NamedQuery(name = "Show.findAll", query = "select s from Show s join fetch s.acts")
public class Show implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String showName;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime startTime;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private  LocalDateTime endTime;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(name = "program")
    private List<Act> acts;

    public Show() {
    }

    public Show(String showName, LocalDateTime startTime, LocalDateTime endTime) {
        this.showName = showName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void addAct(Act act) {
        if(!acts.contains(act)) {
            acts.add(act);
        }
        if(!act.getShows().contains(this)) {
            act.addShow(this);
        }
    }

    public void removeAct(Act act) {
        if(acts.contains(act)) {
            acts.remove(act);
        }
        if(act.getShows().contains(this)) {
            act.removeShow(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Act> getActs() {
        return acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = acts;
    }
}
