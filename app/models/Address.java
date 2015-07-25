package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Embeddable @Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper=false)
public class Address extends Model {

    @Column(length = 50) @NotNull @Constraints.Required
    public String street;

    @Column(length = 10)
    public String suite;

    @Column(length = 25) @NotNull @Constraints.Required
    public String city;

    @Enumerated(value= EnumType.STRING) @NotNull @Constraints.Required
    public State state;

    @Column(length = 5) @NotNull @Constraints.Required @Constraints.Pattern(value = "^\\d{5}$") @Constraints.MaxLength(5) @Constraints.MinLength(5)
    public String zipcode;

    public float lat, lon;

    public static Finder<Long, Address> find = new Finder<>(Long.class, Address.class);

    public enum State {
        AL, AK, AZ, AR, CA, CO, CT, DE, FL, GA, HI, ID, IL, IN, IA, KS, KY, LA, ME, MD, MA, MI, MN, MS, MO, MT, NE, NV, NH, NJ, NM, NY, NC, ND, OH, OK, OR, PA, RI, SC, SD, TN, TX, UT, VT, VA, WA, WV, WI, WY,
    }
}
