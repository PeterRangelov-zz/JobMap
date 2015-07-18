package models;

import com.avaje.ebean.Ebean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper=false)
public class Recruiter extends Model {
    @Id
    public Long id;

    @Constraints.Required @OneToOne(mappedBy = "recruiter", cascade= CascadeType.ALL)
    public User user;

    @ManyToMany(cascade=CascadeType.ALL)
    public List<Site> sites = new ArrayList<Site>();

    @ManyToMany(cascade=CascadeType.ALL)
    public List<Group> groups = new ArrayList<Group>();

    public String emailForCVs;
    
    public static Finder<Long, Recruiter> find = new Finder<Long, Recruiter>(Long.class, Recruiter.class);

}
