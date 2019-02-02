package at.htl.zirkus.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
public class ActMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @XmlTransient
    private Act act;

    public ActMember() {
    }

    public ActMember(Act act) {
        this.act = act;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Act getAct() {
        return act;
    }

    public void setAct(Act newAct) {
        if(this.act != null && this.getAct().getActMembers().contains(this)) {
            this.act.getActMembers().remove(this);
        }
        this.act = newAct;
        if(newAct != null) {
            newAct.addActMember(this);
        }
    }
}
