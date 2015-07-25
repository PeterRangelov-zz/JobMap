package models;

import com.avaje.ebean.annotation.EnumValue;
import com.avaje.ebean.annotation.PrivateOwned;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @Table(name = "er_group") @Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper=false)
public class Group extends Model {
    @Id
    public Long id;

    @Column(name = "group_name", length = 50) @NotNull @Constraints.Required
    public String name;

    @Constraints.Required @Column(length = 50)
    public String logoUrl;

    @Enumerated(value= EnumType.STRING)
    public Type type;

    @Column(name="D")
    public boolean isDemocratic;

    @Column(name="L")
    public boolean isLocal;

    @Column(name="R")
    public boolean isRegional;

    @Column(name="N")
    public boolean isNational;

    @Column(name="P")
    public boolean isPartnershipOpportunity;

    @ManyToMany(mappedBy = "groups")
    public List<Recruiter> recruiters = new ArrayList<>();

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    public List<Site> sites = new ArrayList<>();


    public static Finder<Long, Group> find = new Finder<>(Long.class, Group.class);

    public enum Type {
        @EnumValue("L") LOCAL,
        @EnumValue("R") REGIONAL,
        @EnumValue("N") NATIONAL,
    }
}
