package lee.domain.spec;

import lee.domain.Member;
import lee.utils.DateTimeUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author Leo
 * @create 2018-06-13 上午 12:04
 */
public class MemberSpec {
    public static Specification<Member> findMembers(String start, String end, String name, List<String> ids,String rec){
        return (root, query, cb) -> {
            Path<Date> date = root.get("createdDate");
            Path<Long> id = root.get("id");
            if (!StringUtils.isEmpty(start) || !StringUtils.isEmpty(end)) {
                if (StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)){//如果起始日期为空,则查询小于结束日期
                    cb.lessThanOrEqualTo(date,DateTimeUtils.endTime(end));
                }else if (!StringUtils.isEmpty(start) && StringUtils.isEmpty(end)){//如果结束日期为空,则查询大于起始日期
                    cb.greaterThanOrEqualTo(date,DateTimeUtils.startTime(start));
                }else {// 两者都不为空,则按照范围查询
                    Date startTime = DateTimeUtils.startTime(start);
                    Date endTime = DateTimeUtils.endTime(end);
                    cb.between(root.get("createdDate"),startTime,endTime);
                }
            }else if (!StringUtils.isEmpty(name)){
                String exc = "%" + name + "%";
                cb.like(root.get("name"),exc);
            }
            if (StringUtils.isEmpty(rec)){//回收站标识不存在,查询不在回收站表的member
                return cb.not(id.in(ids));
            }else {
                return cb.isTrue(id.in(ids));
            }
        };
    }
}
