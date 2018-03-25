package lee.domain.spec;

import lee.domain.Task;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * 描述:
 * task类复杂查询条件
 *
 * @author Leo
 * @create 2018-03-25 上午 2:54
 */
public class TaskSpec {
    public static Specification<Task> findByFather(){
        return new Specification<Task>() {
            @Override
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<String> exp1 = root.get("taskName");
                Path<String> exp2 = root.get("fatherId");
                query.distinct(true);
                query.where(cb.like(exp1,"小目标"),cb.equal(exp2,"2"));
                query.groupBy(exp1);
                query.having(cb.equal(cb.count(exp1),2));
                return null;
            }
        };
    }
}
