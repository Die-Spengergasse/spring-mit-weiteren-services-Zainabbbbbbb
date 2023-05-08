package at.spengergasse.at.safeapi.fhir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/communication")
@RestController
public class CommunicationController {

    @Autowired
    ICommunicationRepository communicationRepository;


    @GetMapping("/")
    Iterable<Communication> allData(){return communicationRepository.findAll();}

    @GetMapping("/{id}")
    Optional<Communication> findTestId(@PathVariable Long id){
        Optional <Communication> result = communicationRepository.findById(id);
        return result;
    }

//----------------------------------------------
    @Autowired
    CodingRepository codingRepository;

    @GetMapping("/codings")
    Iterable<Coding> findAllCodings(){
        return codingRepository.findAll();
    }
/*
    @PutMapping("/save/{given}/{family}")
    Communication addPatient(@PathVariable String given, @PathVariable String family){

        return patientRepository.save(
              new Communication(
                      null,
                      2,
                        LocalDate.of(2006,05,07), true,
                        new IdentifierUse(null, IdentifierUse.Useable.usual, "type", "value"),
                        new HumanName(null, HumanName.Useable.nickname),
                        new ContactPoint(null, ContactPoint.System.email, ContactPoint.Useable.mobile, "value")
              ));
    }
    */

}

