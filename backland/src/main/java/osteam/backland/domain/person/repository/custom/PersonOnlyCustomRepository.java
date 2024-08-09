package osteam.backland.domain.person.repository.custom;
import osteam.backland.domain.person.entity.PersonOnly;

import java.util.List;
import java.util.Optional;

public interface PersonOnlyCustomRepository {
    Optional<PersonOnly> findByphone(String phone);
    Optional<List<PersonOnly>> findByphones(String phone);
    Optional<List<PersonOnly>> findByNames(String name);
    Optional<List<PersonOnly>> findAllPerson();
}
