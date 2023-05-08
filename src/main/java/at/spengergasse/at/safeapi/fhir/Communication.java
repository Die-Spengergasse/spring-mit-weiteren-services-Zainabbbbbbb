package at.spengergasse.at.safeapi.fhir;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Communication {

    @GeneratedValue
    @Id
    private Long id;

    public <E> Communication(Object o, String id, String tel, LocalDateTime now, List<E> of) {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    LocalDateTime received;

    public enum Priority{routine,urgent,asap, stat;}
    Priority code;

    @ManyToMany
    List<IdentifierUse> identifier;

    @ManyToMany
    List<CodeableConcept> medium;

    @OneToMany
    List<BackboneElement> payload;

//----------- ab hier nicht Teil der Prüfung---------------------------------------
     public LocalDateTime birthDate;


    @OneToOne(cascade = CascadeType.ALL)
    HumanName name;


    @OneToOne(cascade = CascadeType.ALL)
    ContactPoint telecom;

    @OneToOne(cascade = CascadeType.ALL)
    Address address;

}
