package osteam.backland.domain.phone.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.PersonOneToMany;
import osteam.backland.domain.person.entity.PersonOneToOne;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.phone.entity.PhoneOneToMany;
import osteam.backland.domain.phone.entity.PhoneOneToOne;
import osteam.backland.domain.phone.repository.PhoneOneToOneRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PhoneCreateService {
    private final PhoneOneToOneRepository phoneOneToOneRepository;
    public PhoneOneToOne createOTO(PersonDTO personDTO, PersonOneToOne newPersonOTO){
        PhoneOneToOne newPhoneOTO = new PhoneOneToOne(personDTO.getPhone());
        newPhoneOTO.setPerson(newPersonOTO);
        phoneOneToOneRepository.save(newPhoneOTO);

        return newPhoneOTO;
    }
    public PhoneOneToMany createOTM(PersonDTO personDTO, PersonOneToMany newPersonOTM){
        newPersonOTM.addPhone(personDTO.getPhone());
        List<PhoneOneToMany> phoneOneToMany = newPersonOTM.getPhoneOneToMany();

        return phoneOneToMany.get(0);
    }
}
