package osteam.backland.domain.phone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osteam.backland.domain.phone.entity.PhoneOneToOne;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhoneOneToOneRepository extends JpaRepository<PhoneOneToOne, UUID> {
    Optional<PhoneOneToOne> findByPhone(String phone);
}
