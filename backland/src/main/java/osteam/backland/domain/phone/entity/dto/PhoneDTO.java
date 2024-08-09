package osteam.backland.domain.phone.entity.dto;

import lombok.Builder;
import lombok.Data;
import osteam.backland.domain.person.controller.response.PersonResponse;
import osteam.backland.domain.person.entity.PersonOnly;

@Data
@Builder(toBuilder = true)
public class PhoneDTO {
    private String name;
    private String phone;

    public PhoneDTO(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public static PhoneDTO from(PersonOnly personOnly){
        return new PhoneDTO(personOnly.getName(),personOnly.getPhone());
    }

    public PersonResponse toResponse(){
        return new PersonResponse(name,phone);
    }
}