package osteam.backland.domain.person.repository.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import osteam.backland.domain.person.entity.PersonOnly;

import java.util.List;
import java.util.Optional;

import static osteam.backland.domain.person.entity.QPersonOnly.personOnly;

@Slf4j
@RequiredArgsConstructor
public class PersonOnlyCustomRepositoryImpl implements PersonOnlyCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    //구현 필요.
    @Override
    public Optional<PersonOnly> findByphone(String phone){
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(personOnly)
                        .where(personOnly.phone.eq(phone))
                        .fetchOne()
        );
    }
    @Override
    public Optional<List<PersonOnly>> findByphones(String phone){
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(personOnly)
                        .where(personOnly.phone.eq(phone))
                        .fetch()
        );
    }
    @Override
    public Optional<List<PersonOnly>> findByNames(String name){
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(personOnly)
                        .where(personOnly.name.eq(name))
                        .fetch()
        );
    }
    @Override
    public Optional<List<PersonOnly>> findAllPerson(){
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(personOnly)
                        .fetch()
        );
    }
}
