package at.htl.zirkus.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "Animal.findAll", query = "select a from Animal a")
public class Animal extends ActMember {

    private String name;
    private String kind;

    public Animal() {
    }

    public Animal(Act act, String name, String kind) {
        super(act);
        this.name = name;
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
