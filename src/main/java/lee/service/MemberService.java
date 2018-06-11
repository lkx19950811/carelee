package lee.service;

import lee.domain.Member;
import lee.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author leon
 * @date 2018-06-11 15:31
 * @desc 会员服务类
 */
@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    /**
     * 分页查询 member
     * @param pageable
     * @return
     */
    public List<Member> members(Pageable pageable){
        List<Member> list = memberRepository.findAll();
        return list;
    }
    public Long countMembers(){
        return memberRepository.count();
    }
    public boolean saveMember(Member member){
        Optional<Member> rem = Optional.ofNullable(memberRepository.save(member));
        if (rem.isPresent()){
            return true;
        }else {
            return false;
        }
    }
}
