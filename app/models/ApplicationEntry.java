package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper=false)
public class ApplicationEntry extends Model {
    @Id
    public Long id;

    @ManyToOne
    public Applicant applicant;

    @Constraints.Required
    public LocalDate submitted;

    public Site site;

    public Recruiter recruiter;

    public static Finder<Long, ApplicationEntry> find = new Finder<Long, ApplicationEntry>(Long.class, ApplicationEntry.class);
}
