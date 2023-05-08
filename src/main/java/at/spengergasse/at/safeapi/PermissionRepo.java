package at.spengergasse.at.safeapi;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PermissionRepo extends CrudRepository<Permission,Long> {


    Optional<Permission> findByClassNameAndUserAndObjectId(String className, UserEntity user, Long objectid);

}
