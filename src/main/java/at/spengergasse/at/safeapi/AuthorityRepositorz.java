package at.spengergasse.at.safeapi;

import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepositorz extends CrudRepository<AuthorityEntity, Long> {

    AuthorityEntity findByAuthority(String authority);
}
