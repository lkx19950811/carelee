import lee.domain.Member;
import lee.domain.spec.MemberSpec;
import lee.repository.RecycleRepository;
import lee.service.MemberService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * 描述:
 *
 * @author Leo
 * @create 2018-06-14 下午 9:52
 */
//@TestExecutionListeners({TransactionalTestExecutionListener.class})
public class MemberDelTest extends TestBasic {
    @Autowired
    MemberService memberService;
    @Autowired
    RecycleRepository recycleRepository;
    @Test
    public void test1(){
        List<String> ids = recycleRepository.memberIds();
        Pageable pageable = new PageRequest(0,5);
        Specification<Member> specification = MemberSpec.findMembers("2018-06-10",null,null,ids,"1");
        Page<Member> members = memberService.members(specification,pageable);
        List<Member> members1 = members.getContent();
        System.out.println(members1);
    }

}
