package kr.ranian.bootboard.persistence;

import kr.ranian.bootboard.domain.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ranian129@gmail.com on 2019-09-25
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
public interface MemberRepository extends CrudRepository<Member, String> {

    @Query("select m.uid, count (p) from Member  m left outer join Profile p " +
            " on m.uid = p.member where m.uid = ?1 group by m")
    List<Object[]> getMemberWithProfileCount(String uid);

    @Query("select m, p from  Member m left outer join Profile p " +
            " on m.uid = p.member where m.uid = ?1 and p.current = true")
    List<Object[]> getMemberWithProfile(String uid);
}
