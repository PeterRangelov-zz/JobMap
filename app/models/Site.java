package models;

import com.avaje.ebean.annotation.EnumMapping;
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

    @Constraints.Required @Enumerated(value= EnumType.STRING)
    public SiteKind kind;

    public Set<SiteType> types = new HashSet<SiteType>(6);

    @Embedded
    public Address address;

    @ManyToOne
    public Group group;

    @ManyToMany(mappedBy = "sites")
    public List<Recruiter> recruiters = new ArrayList<Recruiter>();

    public boolean hasGroup;

    @Column(length = 6) @Constraints.Min(0) @Constraints.Max(900000) @Max(900000) @Min(0)
    public int volume;


    public static Finder<Long, Site> find = new Finder<Long, Site>(Long.class, Site.class);

    @EnumMapping(nameValuePairs = "HOSPITAL=H, UCC=U")
    public enum SiteKind {
        HOSPITAL, UCC
    }

    @EnumMapping(nameValuePairs = "COMMUNITY=C, ACADEMIC=A, TRAUMA=T, INNERCITY=I, SUBURBAN=S, RURAL=R")
    public enum SiteType {
        COMMUNITY, ACADEMIC, TRAUMA, INNERCITY, SUBURBAN, RURAL
    }

}
