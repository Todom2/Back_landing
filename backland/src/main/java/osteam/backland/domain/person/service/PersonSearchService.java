package osteam.backland.domain.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import osteam.backland.domain.person.entity.PersonOnly;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.repository.PersonOnlyRepository;
import osteam.backland.global.Exception.model.CustomException;
import osteam.backland.global.Exception.model.ErrorCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonSearchService {
    private final PersonOnlyRepository personOnlyRepository;
    public List<PersonDTO> searchAll(){
        Optional<List<PersonOnly>> personOnlyOptional = personOnlyRepository.findAllPerson();
        if(personOnlyOptional.isEmpty()){
            log.debug("cant find any person in personOnly");
            throw new CustomException(ErrorCode.USER_NOT_FOUND,"사용자를 찾을 수 없음");
        }
        else{
            return personOnlyOptional
                    .orElseGet(ArrayList::new)
                    .stream()
                    .map(PersonDTO::from)
                    .collect(Collectors.toList());
        }
    }
    public List<PersonDTO> searchPerson(String name) {
        Optional<List<PersonOnly>> personOnlyOptional = personOnlyRepository.findByNames(name);
        if (personOnlyOptional.isEmpty()) {
            log.debug("cant find phone in personOnly");
            throw new CustomException(ErrorCode.USER_NOT_FOUND,"사용자를 찾을 수 없음");

        } else {
            return personOnlyOptional
                    .orElseGet(ArrayList::new)
                    .stream()
                    .map(PersonDTO::from)
                    .collect(Collectors.toList());
        }
    }
    public List<PersonDTO> searchPhone(String phone) {
        Optional<List<PersonOnly>> personOnlyOptional = personOnlyRepository.findByphones(phone);
        if (personOnlyOptional.isEmpty()) {
            log.debug("cant find phone in personOnly");
            throw new CustomException(ErrorCode.USER_NOT_FOUND,"사용자를 찾을 수 없음");


        } else {
            return personOnlyOptional
                    .orElseGet(ArrayList::new)
                    .stream()
                    .map(PersonDTO::from)
                    .collect(Collectors.toList());
        }
    }
}
