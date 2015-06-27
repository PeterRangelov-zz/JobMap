package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import play.db.ebean.Model;
import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Hospital extends Model {
    @Id
    public Long id;

    public String name;

    public float lat;

    public float lon;


    public static Finder<Long,Hospital> find = new Finder<Long,Hospital>(Long.class, Hospital.class);
}
