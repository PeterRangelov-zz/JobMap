package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper=false)
public class ApplicationEntry extends Model {
    @Id
    public Long id;

    @ManyToOne (cascade=CascadeType.ALL)
    public Applicant applicant;

    @Constraints.Required @Formats.DateTime(pattern="dd/MM/yyyy") @Column(name = "submitted")
    public Date date;

    @Constraints.Required
    public Site site;

    @Constraints.Required
    public Recruiter recruiter;

    public static Finder<Long, ApplicationEntry> find = new Finder<Long, ApplicationEntry>(Long.class, ApplicationEntry.class);
}