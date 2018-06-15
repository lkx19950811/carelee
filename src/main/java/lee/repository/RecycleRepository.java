package lee.repository;

import lee.domain.Recycle;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


/**
 * @author leon
 * @date 2018-06-14 17:49
 * @desc 回收站仓库类
 */
public interface RecycleRepository extends Repository<Recycle> {
    //查询出所有的会员ID
    @Query("SELECT r.memberId from Recycle r ")
    List<String> memberIds();
    //查询出所有的评论ID
    @Query("SELECT r.comment from Recycle r ")
    List<String> commentIds();
    /**
     * 批量恢复会员
     * @param ids
     * @return 删除的回收站记录数
     */
    @Modifying
    @Transactional
    @Query(value="delete from Recycle r where r.memberId in (:ids) ")
    int deleteByIds(@Param("ids")String[] ids);
}
