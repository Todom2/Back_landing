package osteam.backland.domain.phone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import osteam.backland.domain.person.entity.PersonOneToOne;
import osteam.backland.global.entity.PrimaryKeyEntity;


//주 테이블
@Entity
@Getter
@Setter
public class PhoneOneToOne extends PrimaryKeyEntity {

    private String phone;

    // 연관관계 주인
    @OneToOne(fetch = FetchType.LAZY)
    //Entity 연관관계에서 referencedColumnName가 사용될 때는 FK는 Target Entity에 존재한다.
    @JoinColumn(name = "fk_user_id", referencedColumnName = "id")
    private PersonOneToOne personOneToOne;

    public PhoneOneToOne(String phone) {
        this.phone = phone;
    }

    public PhoneOneToOne() {

    }
    public void setPerson(PersonOneToOne personOneToOne){
        this.personOneToOne = personOneToOne;
    }
}
