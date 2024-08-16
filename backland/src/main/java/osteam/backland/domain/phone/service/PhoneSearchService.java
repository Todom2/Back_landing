package osteam.backland.domain.phone.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.PersonOneToOne;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.phone.entity.PhoneOneToMany;
import osteam.backland.domain.phone.entity.PhoneOneToOne;
import osteam.backland.domain.phone.entity.dto.PhoneDTO;
import osteam.backland.domain.phone.repository.PhoneOneToManyRepository;
import osteam.backland.domain.phone.repository.PhoneOneToOneRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PhoneSearchService {
    private final PhoneOneToOneRepository phoneOneToOneRepository;
    private final PhoneOneToManyRepository phoneOneToManyRepository;
    public Optional<PhoneOneToOne> findPhoneOTO(PersonDTO personDTO){
        return phoneOneToOneRepository.findByPhone(personDTO.getPhone());
    }

    public Optional<PhoneOneToMany> findPhoneOTM(PersonDTO personDTO){
        return phoneOneToManyRepository.findByPhone(personDTO.getPhone());
    }
}
