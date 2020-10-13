# querydsl-init


```java
// querydsl 기본 테스트 코드 

@Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private AcademyRepositorySupport academyRepositorySupport;

@Test
public void querydsl_기본_기능_확인() {
    //given
    String name = "jojoldu";
    String address = "jojoldu@gmail.com";
    academyRepository.save(new Academy(name, address));

    //when
    List<Academy> result = academyRepositorySupport.findByName(name);

    //then
    assertThat(result.size(), is(1));
    assertThat(result.get(0).getAddress(), is(address));
}
```

일반적으로 Querydsl을 사용하면 항상 2개의 Repository를 의존성으로 받아야 한다. (Querydsl의 CustomRepository와 JpaRepository를 상속한 Repository가 기능을 나누어 가졌기 때문)
이를 해결하기 위해 Spring Data Jpa에서는 Custom Repositoy를 JpaRepository 상속 클래스에서 사용할 수 있도록 기능을 지원한다.

![querydsl 구조](https://t1.daumcdn.net/cfile/tistory/99F407345F2ECF4510)

위와 같이 구성하면 기본 Repository에서 RepositoryImpl의 코드도 사용할 수 있다. (Custom이 붙은 인터페이스를 상속한 Impl 클래스의 코드는 Custom 인터페이스를 상속한 JpaRepository에서도 사용 가능)



[spring 공식 문서](https://docs.spring.io/spring-data/jpa/docs/2.1.3.RELEASE/reference/html/#repositories.custom-implementations)

---
[spring boot data jpa - querydsl | jojoldu](https://jojoldu.tistory.com/372)
