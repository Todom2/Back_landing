package osteam.backland.domain.phone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import osteam.backland.domain.person.entity.PersonOneToMany;
import osteam.backland.global.entity.PrimaryKeyEntity;

@Entity
@Getter
@Setter
public class PhoneOneToMany extends PrimaryKeyEntity {

    private String phone;

    // 연관관계 주인
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "fk_person_id",
            referencedColumnName = "id"
    )
    private PersonOneToMany personOneToMany;
    public PhoneOneToMany(PersonOneToMany personOneToMany, String phone) {
        this.personOneToMany = personOneToMany;
        this.phone = phone;
    }
    public PhoneOneToMany() {

    }
    public void setPerson(PersonOneToMany personOneToMany){
        this.personOneToMany = personOneToMany;
    }
}
