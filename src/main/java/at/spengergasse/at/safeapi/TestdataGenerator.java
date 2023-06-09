package at.spengergasse.at.safeapi;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.awt.*;
import java.util.stream.StreamSupport;

@Component
@AllArgsConstructor
public class TestdataGenerator implements CommandLineRunner {

    UserRepository userRepository;

    AuthorityRepositorz authorityRepository;

    @Override
    public void run(String... args) throws Exception {

        var users = StreamSupport.stream(userRepository.findAll().spliterator(), true).toList();
        var authorities = StreamSupport.stream(authorityRepository.findAll().spliterator(), true).toList();

        AuthorityEntity read= new AuthorityEntity(null, "read");
        AuthorityEntity write= new AuthorityEntity(null, "write");
        AuthorityEntity delete= new AuthorityEntity(null, "delete");
        AuthorityEntity grant= new AuthorityEntity(null, "grant");
        AuthorityEntity owner= new AuthorityEntity(null, "owner");

        AuthorityEntity adminAuthority= new AuthorityEntity(null, "admin");

        authorityRepository.saveAll(
                List.of(read, write, delete, grant, owner, adminAuthority));

        var admin = new UserEntity(
                null,"admin",
                "admin",
                true,
                false,
                false,
                List.of(write, read, adminAuthority));

        userRepository.save(admin);
    }
}
