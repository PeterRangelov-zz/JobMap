package models;

import com.avaje.ebean.annotation.EnumMapping;
import com.avaje.ebean.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper=false)
public class Site extends Model {
    @Id
    public Long id;

    @Constraints.Required @Column(name = "site_name", length = 50)
    public String name;

    @Constraints.Required @Column(length = 50)
    public String logoUrl;

    @Enumerated(value= EnumType.STRING)
    public Type type;

    @Enumerated(value= EnumType.STRING)
    public Territory territory;

    @Column(name="C")
    public boolean isCommunity;

    @Column(name="A")
    public boolean isAcademic;

    @Column(name="T")
    public boolean isTrauma;

    @Embedded
    public Address address;

    @ManyToOne(cascade = CascadeType.ALL)
    public Group group;

    @ManyToMany(mappedBy = "sites", cascade = CascadeType.ALL)
    public List<Recruiter> recruiters = new ArrayList<>();

    public boolean hasGroup;

    @Column(length = 6) @Constraints.Min(0) @Constraints.Max(900000) @Max(900000) @Min(0)
    public int volume;

    public static Finder<Long, Site> find = new Finder<>(Long.class, Site.class);

    public enum Type {
        @EnumValue("H") HOSPITAL,
        @EnumValue("U") UCC,
        @EnumValue("F") FREESTANDING
    }

    public enum Kind {
        @EnumValue("C") COMMUNITY,
        @EnumValue("A") ACADEMIC,
        @EnumValue("T") TRAUMA,
        @EnumValue("I") INNER_CITY,
        @EnumValue("S") SUBURBAN,
        @EnumValue("R") RURAL
    }

    public enum Territory {
        @EnumValue("I") INNER_CITY,
        @EnumValue("S") SUBURBAN,
        @EnumValue("R") RURAL,
        @EnumValue("D") DOWNTOWN
    }

}
