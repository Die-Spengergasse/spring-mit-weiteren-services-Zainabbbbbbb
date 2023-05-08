package at.spengergasse.at.safeapi;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestRessourceController {

    TestRessourceRessourceRepo testRessourceRessourceRepo;

    UserRepository userRepository;

    PermissionChecker permissionChecker;

    PermissionRepo permissionRepo;

    @GetMapping("/")
    Iterable <TestRessource> allData(){
        return testRessourceRessourceRepo.findAll();
    }

    boolean isAuthValid(String authHeader){
        if (authHeader==null){
            return false;
        }

        String[] authDetails = authHeader.split(" ");

        if (authDetails.length != 2){
            return false;
        }

        Optional<UserEntity> givenUser= userRepository.findByUsernameAndPassword(authDetails[0], authDetails[1]);

        if (givenUser.isEmpty()){
           //user not found, return false
            return false;
        }else{
            //user exists, credentials match
            return true;
        }
    }

    @GetMapping("/{id}")
    Optional<TestRessource> findTestId(@PathVariable Long id, Authentication auth, HttpServletResponse response) {

        //todo

        var permissions = StreamSupport.stream(permissionRepo.findAll().spliterator(), false).toArray();

        boolean isAllowed = permissionChecker.checkPermission((String) auth.getName(), "TestRessource", id, PermissionLevel.READ);

        if (!isAllowed)
        {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return Optional.empty();
        }

        Optional<TestRessource> result = testRessourceRessourceRepo.findById(id);
        if (result.isEmpty()) {
            response.setStatus(404);
        }
        return result;
    }

    @PutMapping("/")

    TestRessource addTestResource(Authentication auth, @RequestBody TestRessource testRessource){

       var savedResource = testRessourceRessourceRepo.save(testRessource);

        var user = userRepository.findByUsername((String)auth.getName()).get();
        Permission testResourcePermission = new Permission(
                null,
                user,
                "TestRessource",
                savedResource.getId(),
                PermissionLevel.OWNER
        );

        permissionRepo.save(testResourcePermission);

        return savedResource;
    }

}
