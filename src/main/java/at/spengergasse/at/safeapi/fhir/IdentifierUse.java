package at.spengergasse.at.safeapi.fhir;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Period;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class IdentifierUse {;

    @GeneratedValue
    @Id
    private Long id;

    public IdentifierUse(Object o, Useable usual, String type, String value) {
    }

    public void setId(Long id) {
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public enum Useable {usual, official, temp, secondary};

    Useable u;

    @OneToMany
    List<CodeableConcept> type;

    String system;

    String value;

    Period period;

}
