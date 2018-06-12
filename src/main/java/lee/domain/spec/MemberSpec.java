package lee.domain.spec;

import lee.domain.Member;
import lee.utils.DateTimeUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Date;

/**
 * 描述:
 *
 * @author Leo
 * @create 2018-06-13 上午 12:04
 */
public class MemberSpec {
    public static Specification<Member> findMembers(String start,String end,String name){
        return (root, query, cb) -> {
            Predicate predicate = null;
            Path<Date> date = root.get("createdDate");
            if (!StringUtils.isEmpty(start) || !StringUtils.isEmpty(end)) {
                if (StringUtils.isEmpty(start) && !StringUtils.isEmpty(end)){//如果起始日期为空,则查询小于结束日期
                    predicate = cb.lessThanOrEqualTo(date,DateTimeUtils.endTime(end));
                }else if (!StringUtils.isEmpty(start) && StringUtils.isEmpty(end)){//如果结束日期为空,则查询大于起始日期
                    predicate= cb.greaterThanOrEqualTo(date,DateTimeUtils.startTime(start));
                }else {// 两者都不为空,则按照范围查询
                    Date startTime = DateTimeUtils.startTime(start);
                    Date endTime = DateTimeUtils.endTime(end);
                    predicate = cb.between(root.get("createdDate"),startTime,endTime);
                }
            }else if (!StringUtils.isEmpty(name)){
                String exc = "%" + name + "%";
                predicate = cb.like(root.get("name"),exc);
            }
            return predicate==null?null:predicate;
        };
    }
}
