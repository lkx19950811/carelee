package lee.repository;

import lee.domain.Recycle;
import org.springframework.data.jpa.repository.Query;

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
    //通过会员Id删除回收站记录
    int deleteByMemberId(String Id);
}
