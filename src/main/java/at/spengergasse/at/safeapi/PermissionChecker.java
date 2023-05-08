package at.spengergasse.at.safeapi;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PermissionChecker {

    PermissionRepo permissionRepo;

    UserRepository userRepository;

    AuthorityRepositorz authorityRepositorz;

    public boolean isAdmin(String username){
        var userOption = userRepository.findByUsername(username);

        if(userOption.isEmpty()){
            return false;
        } else{

            var adminAuth = authorityRepositorz.findByAuthority("admin");
            var user = userOption.get();
            return user.getAuthorities().contains(adminAuth);
        }
    }

    public boolean hasPermissionInRepo(String classname, PermissionLevel requestedPermissionLevel, String user, Long objectId){

     var userEntity = userRepository.findByUsername(user).get();

      var permissionOption=  permissionRepo.findByClassNameAndUserAndObjectId(
              classname,
              userEntity,
              objectId);

        return permissionOption.isPresent() &&
                permissionOption.get().getPermissionLevel().ordinal() <= requestedPermissionLevel.ordinal();
    }

    public boolean isOwner(String username, String className, Long objectId) {

        return this.hasPermissionInRepo(className, PermissionLevel.OWNER, username, objectId);
    }

    public boolean checkPermission(String name, String className, Long objectid, PermissionLevel permissionName) {
        return isAdmin(name) ||
                hasPermissionInRepo(className, permissionName, name, objectid ) ||
                isOwner(name,className,objectid);
    }

}
