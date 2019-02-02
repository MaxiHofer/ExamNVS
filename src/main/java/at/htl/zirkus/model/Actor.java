package at.htl.zirkus.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "Actor.findAll", query = "select a from Actor a")
public class Actor extends ActMember {

    private String firstName;
    private String lastName;
    private String socialSecurityNr;

    public Actor() {
    }

    public Actor(Act act, String firstName, String lastName, String socialSecurityNr) {
        super(act);
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNr = socialSecurityNr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialSecurityNr() {
        return socialSecurityNr;
    }

    public void setSocialSecurityNr(String socialSecurityNr) {
        this.socialSecurityNr = socialSecurityNr;
    }
}
