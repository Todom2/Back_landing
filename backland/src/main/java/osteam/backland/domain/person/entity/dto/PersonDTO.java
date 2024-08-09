package osteam.backland.domain.person.entity.dto;

import lombok.Builder;
import lombok.Data;
import osteam.backland.domain.person.controller.response.PersonResponse;
import osteam.backland.domain.person.entity.PersonOnly;

@Data
@Builder(toBuilder = true)
public class PersonDTO {
    private String name;
    private String phone;

    public PersonDTO(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public static PersonDTO from(PersonOnly personOnly){
        return new PersonDTO(personOnly.getName(),personOnly.getPhone());
    }

    public PersonResponse toResponse(){
        return new PersonResponse(name,phone);
    }
}
