package at.spengergasse.at.safeapi.fhir;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface CodingRepository extends CrudRepository<Coding, Long> {
}
