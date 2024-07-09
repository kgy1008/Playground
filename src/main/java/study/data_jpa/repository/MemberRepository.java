package study.data_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.data_jpa.domain.Member;
import study.data_jpa.dto.MemberDto;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 메소드 이름을 분석해서 JPQL을 생성해준다.
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    // NamedQuery 사용
    @Query(name = "Member.findByUsername")  // 없어도 됨 -> 어짜피 1순위가 NamedQuery이기 때문
    List<Member> findByUsername(@Param("username") String username);

    // 리포지토리 메소드에 쿼리 정의하기
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.data_jpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);
}