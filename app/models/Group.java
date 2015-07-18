package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "er_group") @Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper=false)
public class Group extends Model {
    @Id
    public Long id;

    @Column(name = "group_name", length = 50) @NotNull @Constraints.Required
    public String name;

    @ManyToMany(mappedBy = "groups", cascade=CascadeType.ALL)
    public List<Recruiter> recruiters = new ArrayList<Recruiter>();

    @OneToMany(mappedBy = "group", cascade=CascadeType.ALL)
    public List<Site> sites = new ArrayList<Site>();


    public static Finder<Long, Group> find = new Finder<Long, Group>(Long.class, Group.class);

    public enum Type {
        DEMOCRATIC, LOCAL, REGIONAL, NATIONAL
    }
}
