package kr.ranian.bootsecurity.persistence;

import kr.ranian.bootsecurity.domain.Member;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ranian129@gmail.com on 2019-10-17
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 */
public interface MemberRepository extends CrudRepository<Member, String> {
}
