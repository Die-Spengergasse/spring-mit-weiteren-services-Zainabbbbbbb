package at.spengergasse.at.safeapi;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

 Optional<UserEntity> findByUsernameAndPassword(String username, String password);
 Optional<UserEntity> findByUsername(String username);

}
