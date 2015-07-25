package models;

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
public class Applicant extends Model {
    @Id
    public Long id;

    @OneToOne(cascade=CascadeType.ALL)
    public User user;

    @Embedded
    public CV cv;

    public List<ApplicationEntry> applicationHistory = new ArrayList<>();

    public static Finder<Long, Applicant> find = new Finder<>(Long.class, Applicant.class);
}
