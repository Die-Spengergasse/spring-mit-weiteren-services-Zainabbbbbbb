package at.spengergasse.at.safeapi;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserRepository userRepository;
    PermissionRepo permissionRepo;

    @GetMapping("/register/{username}/{password}")
    UserEntity register(@PathVariable String username, @PathVariable String password) {
        return userRepository.save(
                new UserEntity(null, password, username, true, false, false, List.of())
        );
    }

    @GetMapping("/login/{username}/{password}")
    void login(@PathVariable String username, @PathVariable String password, HttpServletResponse resonse) {
        if (userRepository.findByUsernameAndPassword(username, password).isPresent()) {
            resonse.setStatus(200);
        } else {
            resonse.setStatus(401);
        }
    }

    @PostMapping("/name")
    String getUsername(Authentication authentication){
        return authentication.getName();
    }

    @PutMapping("/grant/{grantedUsername}/{permissionType}/{className}/{objectId}")
    Permission grantPermission(Authentication authentication,
                               @PathVariable String grantedUsername,
                               @PathVariable PermissionLevel permissionType,
                               @PathVariable String className,
                               @PathVariable Long objectId
                              // @PathVariable Entity entity  für fhir dann
    ){
        var grantedUserOption = userRepository.findByUsername(grantedUsername);
        var granteeUserOption = userRepository.findByUsername( authentication.getName());

        //className.getClass().getName(String );

        var permissionOption = permissionRepo.findByClassNameAndUserAndObjectId(
                "TestRessource",
                granteeUserOption.get(),
                objectId);

        //if grantee is allowed to grant, add new Permission
        if (permissionOption.isPresent() ) {
            return permissionRepo.save(new Permission(
                    null,
                    grantedUserOption.get(),
                    className,
                    objectId,
                    permissionType)
            );
        }
            return null;
    }


 //   @GetMapping("/grant/{grantingUsername}/{password}/{grantedUsername}/{permissionType}/{className}/{objectId}")
 //   Permission grantPermission(
 //           @PathVariable String grantingUsername,
 //           @PathVariable String password,
 //           @PathVariable String grantedUsername,
 //           @PathVariable PermissionLevel permissionType,
 //           @PathVariable String className,
 //           @PathVariable Long objectId,
 //           HttpServletResponse response
 //   ) {
//
 //       var grantedUserOption = userRepository.findByUsername(grantedUsername);
//
 //       if (grantedUserOption.isPresent() ) {
 //           var newPermission = new Permission(null, grantedUserOption.get(), className, objectId, permissionType);
//
//
 //           var grantingUser = userRepository.findByUsernameAndPassword(grantingUsername, password);
//
 //           if (grantingUser.isPresent()) {
 //               permissionRepo.save(newPermission);
//
 //           } else {
 //               response.setStatus(401);
 //               return null;
 //           }
//
 //       }
 //       else {
 //           response.setStatus(400);
 //           return null;
 //       }
//
 //       return null;
 //   }

    }