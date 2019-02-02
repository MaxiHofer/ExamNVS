package at.htl.zirkus.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;

@Entity
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
}
