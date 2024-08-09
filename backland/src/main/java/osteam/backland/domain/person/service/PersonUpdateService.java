package osteam.backland.domain.person.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import osteam.backland.domain.person.entity.PersonOneToMany;
import osteam.backland.domain.person.entity.PersonOneToOne;
import osteam.backland.domain.person.entity.PersonOnly;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.phone.entity.PhoneOneToMany;
import osteam.backland.domain.phone.entity.PhoneOneToOne;

@Service
@Slf4j
public class PersonUpdateService {
    public PersonDTO updateOTM(PersonDTO personDTO, PhoneOneToMany phoneOneToMany){
        PersonOneToMany personOTM = phoneOneToMany.getPersonOneToMany();
        // 1-1. update person
        personOTM.update(personDTO.getName());
        return new PersonDTO(personOTM.getName(),phoneOneToMany.getPhone());
    }
    public PersonDTO updateOTO(PersonDTO personDTO, PhoneOneToOne phoneOneToOne){
        PersonOneToOne personOTO = phoneOneToOne.getPersonOneToOne();
        personOTO.update(personDTO.getName());
        return new PersonDTO(personOTO.getName(),phoneOneToOne.getPhone());
    }
    public PersonDTO updateOne(PersonDTO personDTO){
        PersonOnly personOnly = new PersonOnly(personDTO.getName(), personDTO.getPhone());
        // 1-1. update person
        personOnly.update(personDTO.getName());
        personDTO.setName(personOnly.getName());
        return personDTO;
    }
}
