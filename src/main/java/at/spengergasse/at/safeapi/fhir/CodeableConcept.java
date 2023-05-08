package at.spengergasse.at.safeapi.fhir;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CodeableConcept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable = false)
    private Long id;

    @Column(nullable = false)
    String text;

    @OneToMany
    List<Coding> coding;


}
