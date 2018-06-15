package lee.domain.spec;

import lee.domain.Member;
import lee.utils.DateTimeUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author Leo
 * @create 2018-06-13 上午 12:04
 */
public class MemberSpec {
    /**
     * 根据条件查找member
     * @param start 开始日期(创建时间)
     * @param end 结束日期(创建时间)
     * @param name 用户名
     * @param ids member的id
     * @param rec 是否回收(决定是否将传入的ids排除)
     * @return
     */
    public static Specification<Member> findMembers(String start, String end, String name, List<String> ids,String rec){
        return (root, query, cb) -> {
            Path<Date> date = root.get("createdDate");
            Path<Long> id = root.get("id");
            List<Predicate> list = new ArrayList<>();//创建条件空list
            if (ids!=null && ids.size()>0){
                if (StringUtils.isEmpty(rec)){//回收站标识不存在,查询不在回收站表的member
                    list.add(cb.not(id.in(ids)));
                }else {
                    list.add(cb.isTrue(id.in(ids)));
                }
            }
            if (!StringUtils.isEmpty(start) || !StringUtils.isEmpty(end)) {
                if (StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)){//如果起始日期为空,则查询小于结束日期
                    list.add(cb.lessThanOrEqualTo(date,DateTimeUtils.endTime(end)));
                }else if (!StringUtils.isEmpty(start) && StringUtils.isEmpty(end)){//如果结束日期为空,则查询大于起始日期
                    list.add(cb.greaterThanOrEqualTo(date,DateTimeUtils.startTime(start)));
                }else {// 两者都不为空,则按照范围查询
                    Date startTime = DateTimeUtils.startTime(start);
                    Date endTime = DateTimeUtils.endTime(end);
                    list.add(cb.between(root.get("createdDate"),startTime,endTime));
                }
            }else if (!StringUtils.isEmpty(name)){//名字不为空则模糊查询名字
                String exc = "%" + name + "%";
                list.add(cb.like(root.get("name"),exc));
            }
            return cb.and(list.toArray(new Predicate[list.size()]));//将list中的条件转为数组
        };
    }
}
