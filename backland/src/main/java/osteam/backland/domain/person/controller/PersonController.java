package osteam.backland.domain.person.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osteam.backland.domain.person.controller.request.PersonCreateRequest;
import osteam.backland.domain.person.controller.response.PersonResponse;
import osteam.backland.domain.person.entity.dto.PersonDTO;
import osteam.backland.domain.person.service.PersonCreateService;
import osteam.backland.domain.person.service.PersonSearchService;
import osteam.backland.domain.person.service.PersonUpdateService;
import osteam.backland.domain.phone.entity.dto.PhoneDTO;
import osteam.backland.global.Exception.model.CustomException;
import osteam.backland.global.Exception.model.ErrorCode;

import java.util.List;

/**
 * PersonController
 * 등록 수정 검색 구현
 * <p>
 * 등록 - 입력된 이름과 전화번호를 personOnly, personOneToOne, personOneToMany에 저장
 * 수정 - 이미 등록된 전화번호가 입력될 경우 해당 전화번호의 소유주 이름을 변경
 * 검색 - 전체 출력, 이름으로 검색, 전화번호로 검색 구현, 검색은 전부 OneToMany 테이블로 진행.
 */
@Slf4j
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonCreateService personCreateService;
    private final PersonSearchService personSearchService;
    /**
     * 등록 기능
     * personRequest를 service에 그대로 넘겨주지 말 것.
     *
     * @param personCreateRequest
     * @return 성공 시 이름 반환
     */
    @PostMapping
    public String person(@RequestBody(required = false) PersonCreateRequest personCreateRequest) {
        if(personCreateRequest==null){
            throw new CustomException(ErrorCode.OK,"Request 가 null 입니다.");
        }
        if(personCreateRequest.getName() == null || personCreateRequest.getPhone() == null){
            throw new CustomException(ErrorCode.OK,"name,person 이 null 입니다.");
        }
        // 리퀘스트로부터 이름과 전화번호를 받아 서비스로 넘겨줌.
        // 만약 중복된 전화번호라면 전화번호의 이름을 변경!
        PersonDTO personDTO = new PersonDTO(personCreateRequest.getName(),personCreateRequest.getPhone());
        return personCreateService.createAll(personDTO).getName();
    }

    /**
     * 전체 검색 기능
     */
    @GetMapping
    public ResponseEntity<List<PersonResponse>> getPeople() {
        List<PersonResponse> persons = personSearchService.searchAll().stream()
                .map(PersonDTO::toResponse)
                .toList();
        return ResponseEntity.ok(persons);
    }

    /**
     * 이름으로 검색
     *
     * @param name
     */
    @GetMapping("/name")
    public ResponseEntity<List<PersonResponse>> getPeopleByName(@RequestParam(name = "name") String name){
        List<PersonResponse> persons = personSearchService.searchPerson(name).stream()
                .map(PersonDTO::toResponse)
                .toList();
        return ResponseEntity.ok(persons);
    }

    /**
     * 번호로 검색
     *
     * @param phone
     */
    @GetMapping("/phone")
    public ResponseEntity<List<PersonResponse>> getPeopleByPhone(@RequestParam(name = "phone") String phone) {
        List<PersonResponse> persons = personSearchService.searchPhone(phone).stream()
                .map(PersonDTO::toResponse)
                .toList();
        return ResponseEntity.ok(persons);
    }
}
