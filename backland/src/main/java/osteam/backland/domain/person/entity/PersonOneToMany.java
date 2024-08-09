package osteam.backland.domain.person.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import osteam.backland.domain.phone.entity.PhoneOneToMany;
import osteam.backland.global.entity.PrimaryKeyEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class PersonOneToMany extends PrimaryKeyEntity {

    private String name;

    // 부모 엔티티
    @OneToMany(
            mappedBy = "personOneToMany",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<PhoneOneToMany> phoneOneToMany = new ArrayList<>();

    public PersonOneToMany(String name) {
        this.name = name;
    }

    public PersonOneToMany() {

    }
    public void addPhone(String phone){
        this.phoneOneToMany.add(new PhoneOneToMany(this,phone));
    }

    public void update(String name){
        this.name = name;
    }
}
