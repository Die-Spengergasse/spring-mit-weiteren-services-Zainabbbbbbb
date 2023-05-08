package at.spengergasse.at.safeapi.fhir;

import org.springframework.data.repository.CrudRepository;

public interface ICommunicationRepository extends CrudRepository<Communication, Long> {

}
