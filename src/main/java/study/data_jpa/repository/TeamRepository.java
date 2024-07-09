package study.data_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.data_jpa.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    // 구현체를 SpringdateJPA가 자동으로 생성해준다.
}
