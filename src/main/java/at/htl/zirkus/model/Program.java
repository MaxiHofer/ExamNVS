package at.htl.zirkus.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Program implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Act> acts;

    public Program() {
    }

    public Program(List<Act> acts) {
        this.acts = acts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Act> getActs() {
        return acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = acts;
    }
}
