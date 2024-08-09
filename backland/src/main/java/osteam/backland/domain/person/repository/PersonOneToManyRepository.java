package osteam.backland.domain.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osteam.backland.domain.person.entity.PersonOneToMany;

import java.util.UUID;

public interface PersonOneToManyRepository extends JpaRepository<PersonOneToMany, UUID> {

}
