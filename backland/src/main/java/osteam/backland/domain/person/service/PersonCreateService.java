package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import osteam.backland.domain.person.entity.PersonOneToMany;
import osteam.backland.domain.person.entity.PersonOneToOne;
import osteam.backland.domain.person.entity.PersonOnly;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.repository.PersonOneToManyRepository;
import osteam.backland.domain.person.repository.PersonOneToOneRepository;
import osteam.backland.domain.person.repository.PersonOnlyRepository;
import osteam.backland.domain.phone.entity.PhoneOneToMany;
import osteam.backland.domain.phone.entity.PhoneOneToOne;
import osteam.backland.domain.phone.repository.PhoneOneToManyRepository;
import osteam.backland.domain.phone.repository.PhoneOneToOneRepository;
import osteam.backland.domain.phone.service.PhoneCreateService;
import osteam.backland.domain.phone.service.PhoneSearchService;
import osteam.backland.global.Exception.model.CustomException;
import osteam.backland.global.Exception.model.ErrorCode;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonCreateService {
    //전체적인 추가 - Create 에 있는 Update 내용을 UpdateService 로 이전
    //  아마 컨트롤러에서 Create 와 Update 를 분기해야할것 같다.
    private final PersonOnlyRepository personOnlyRepository;
    private final PersonOneToOneRepository personOneToOneRepository;
    private final PersonOneToManyRepository personOneToManyRepository;
    private final PhoneOneToManyRepository phoneOneToManyRepository;
    private final PersonUpdateService personUpdateService;

    private final PhoneCreateService phoneCreateService;
    private final PhoneSearchService phoneSearchService;

    @Transactional
    public PersonDTO createAll(PersonDTO personDTO) {
        if(personDTO.getName().isBlank() || personDTO.getPhone().isBlank()){
            throw new CustomException(ErrorCode.OK,"name,person 이 null 입니다.");
        }
        oneToMany(personDTO);
        oneToOne(personDTO);
        one(personDTO);
        return personDTO.toBuilder().build();
    }

    /**
     * Phone과 OneToOne 관계인 person 생성
     */
    @Transactional
    public PersonDTO oneToOne(PersonDTO personDTO) {
        Optional<PhoneOneToOne> phoneOneToOne = phoneSearchService.findPhoneOTO(personDTO);
        //1. if already has phone number
        if(phoneOneToOne.isPresent()){
            return personUpdateService.updateOTO(personDTO, phoneOneToOne.get());
        }
        //2. no number in DB
        else{
            // 2-1. create new person, phone
            PersonOneToOne newPersonOTO = new PersonOneToOne(personDTO.getName());
            // 2-2. save person
            personOneToOneRepository.save(newPersonOTO);
            // 2-3. save phone
            PhoneOneToOne newPhoneOTO = phoneCreateService.createOTO(personDTO, newPersonOTO);
            // 2-4. Entity -> DTO
            return new PersonDTO(newPersonOTO.getName(),newPhoneOTO.getPhone());
        }
    }

    /**
     * Phone과 OneToMany 관계인 person 생성
     */
    // 추가 - 동일한 폰 번호가 들어올 때, 사람 이름 수정하는 로직(Update)추가
    @Transactional
    public PersonDTO oneToMany(PersonDTO personDTO) {
        Optional<PhoneOneToMany> phoneOneToMany = phoneSearchService.findPhoneOTM(personDTO);
        //1. if already has phone number
        if(phoneOneToMany.isPresent()){
            return personUpdateService.updateOTM(personDTO,phoneOneToMany.get());
        }
        else{
            PersonOneToMany personOTM = new PersonOneToMany(personDTO.getName());
            personOneToManyRepository.save(personOTM);

            PhoneOneToMany phoneOTM = phoneCreateService.createOTM(personDTO,personOTM);

            return new PersonDTO(personOTM.getName(),phoneOTM.getPhone());
        }
    }

    /**
     * person 하나로만 구성되어 있는 생성
     */
    @Transactional
    public PersonDTO one(PersonDTO personDTO) {
        Optional<PersonOnly> personOnly = personOnlyRepository.findByphone(personDTO.getPhone());
        // 1. if already has phone number
        if(personOnly.isPresent()){
            return personUpdateService.updateOne(personDTO);
        }
        //2. no number in DB
        else{
            // 2-1. create new personOnly
            PersonOnly newPerson = new PersonOnly(personDTO.getName(),personDTO.getPhone());
            // 2-2. save person
            personOnlyRepository.save(newPerson);
            // 2-3. Entity -> DTO
            return new PersonDTO(newPerson.getName(),newPerson.getPhone());
        }
    }
}
