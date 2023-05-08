package at.spengergasse.at.safeapi.fhir;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Period;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter


public class HumanName {

    @GeneratedValue
    @Id
    private Long id;

    public HumanName(Object o, Useable nickname) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enum Useable {usual, official, temp, nickname, anonymous, old};

    Useable usea;

    String text;

    String family;

    String given;

    String prefix;

    String suffix;

    Period period;

}
