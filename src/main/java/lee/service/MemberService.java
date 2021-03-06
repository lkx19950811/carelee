package lee.service;

import lee.domain.Member;
import lee.domain.Recycle;
import lee.repository.MemberRepository;
import lee.repository.RecycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    RecycleRepository recycleRepository;
    /**
     * 分页查询 member
     * @param pageable
     * @return
     */
    public Page<Member> members(Pageable pageable){
        Page<Member> members = memberRepository.findAll(pageable);
        return members;
    }
    public Page<Member> members(Specification<Member> specification,Pageable pageable){
        Page<Member> members = memberRepository.findAll(specification,pageable);
        return members;
    }
    /**
     * 保存会员信息
     * @param member
     * @return 会员信息
     */
    public Optional<Member> saveMember(Member member){
        Optional<Member> rem = Optional.ofNullable(memberRepository.save(member));
        return rem;
    }

    /**
     * 根据id找到会员
     * @param Id
     * @return
     */
    public Optional<Member> findMemberById(Long Id){
        Optional<Member> optionalMember = Optional.ofNullable(memberRepository.findOne(Id));
        return optionalMember;
    }

    /**
     * 根据id删除单个会员
     * @param id
     */
    public void delByid(Long id){
        memberRepository.delete(id);
    }

    /**
     * 根据 {ids}批量删除会员
     * @param ids
     * @return
     */
    public int delByids(Long[] ids){
        List<Long> list = Arrays.asList(ids);
        return memberRepository.deleteByIds(list);
    }
    public Long countMember(){
        return memberRepository.count();
    }

    public List<Recycle> putRec(String ...ids){
        List<Recycle> recycles = new ArrayList<>();
        for (String id: ids ){
            Recycle recycle = new Recycle();
            recycle.setMemberId(id);
            recycles.add(recycle);
        }
        return recycleRepository.save(recycles);
    }
}
