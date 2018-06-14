package lee.repository;

import lee.domain.Member;
import lee.domain.Recycle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author leon
 * @date 2018-06-11 15:28
 * @desc 会员仓库类
 */
public interface MemberRepository extends Repository<Member> {
    /**
     * 批量删除会员接口
     * @param ids
     * @return
     */
    @Modifying
    @Transactional
    @Query(value="delete from Member m where m.id in (:ids) ")
    int deleteByIds(@Param("ids")List<Long> ids);
//TODO 找出没有在回收站里的member
//    @Query("select m from Member m left join fetch Member.Re on m.id = Recyle.delId where Recyle.classType='member'")
//    List<Member> findUndelMember();
}
