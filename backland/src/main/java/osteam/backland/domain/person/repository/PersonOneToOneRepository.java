package osteam.backland.domain.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osteam.backland.domain.person.entity.PersonOneToOne;

import java.util.UUID;

//UUID = String인데 random숫자, 같은게 나올 확률이 사실상 0이므로 unique함 거의 보장되는 id.
@Repository
public interface PersonOneToOneRepository extends JpaRepository<PersonOneToOne, UUID>{

}
