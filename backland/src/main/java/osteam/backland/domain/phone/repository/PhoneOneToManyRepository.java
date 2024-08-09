package osteam.backland.domain.phone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osteam.backland.domain.phone.entity.PhoneOneToMany;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhoneOneToManyRepository extends JpaRepository<PhoneOneToMany, UUID> {
    Optional<PhoneOneToMany> findByPhone(String phone);
}
