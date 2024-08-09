package osteam.backland.domain.person.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import osteam.backland.global.entity.PrimaryKeyEntity;

@Entity
@Getter
@Setter
public class PersonOnly extends PrimaryKeyEntity {
    @NotNull
    private String name;

    @NotNull
    private String phone;

    public PersonOnly(String name, String phone) {
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)가 들어왔습니다",name));
        }
        if(phone==null || phone.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 phone(%s)가 들어왔습니다",phone));
        }
        this.name = name;
        this.phone = phone;
    }

    public PersonOnly() {

    }

    public void update(String name){
        this.name = name;
    }
}
