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

public class ContactPoint {

    @GeneratedValue
    @Id
    private Long id;

    public enum System{phone, fax, email, pager, url, sms, other};
    System s;


    public enum Useable {home,work ,temp,old , mobile}
    Useable u;

    String value;


}