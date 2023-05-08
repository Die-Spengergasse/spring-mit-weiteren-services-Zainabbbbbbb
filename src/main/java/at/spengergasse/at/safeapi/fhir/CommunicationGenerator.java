package at.spengergasse.at.safeapi.fhir;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
@AllArgsConstructor
public class CommunicationGenerator implements CommandLineRunner {

    ICommunicationRepository communicationRepository;

    @Override
    public void run(String... args) throws Exception {

        Communication c1 = new Communication(null,
                "id",
                "tel",
                LocalDateTime.now(),
                List.of()
        );

                communicationRepository.save(c1);

    /*
    Communication read= new Communication(null,
            LocalDate.of(2022,01,01),
            Communication.Priority.routine,
            new IdentifierUse(null, IdentifierUse.Useable.usual, "type", "value"), "read")
            );

        var c = Communication.builder()
                .received(LocalDateTime.now())
                .identifier(new IdentifierUse(null, IdentifierUse.Useable.usual, "type", "value"))
                .build();

     */
    }
}

