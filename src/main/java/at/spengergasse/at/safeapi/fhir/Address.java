package at.spengergasse.at.safeapi.fhir;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Period;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter


public class Address {


    @GeneratedValue
    @Id
    private Long id;


    public enum Use {home , work , temp , old ,billing};
    Use u;
    public enum Type {postal, physical, both};
    Type t;

    String text;
    String line;
    String city;
    String district;
    String state;
    String postalCode;
    Period period;

}
