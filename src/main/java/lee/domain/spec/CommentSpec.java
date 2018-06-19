package lee.domain.spec;

import lee.domain.Comments;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author leon
 * @date 2018-06-15 17:58
 * @desc 评论复杂查询类
 */
public class CommentSpec {
    /**
     * 根据条件查找member
     * @param movieName 用户名
     * @param ids member的id
     * @param rec 是否回收(决定是否将传入的ids排除)
     * @return
     */
    public static Specification<Comments> findComments(String movieName, List<String> ids, String rec){
        return (root, query, cb) -> {
            Path<Date> id = root.get("commentId");
            List<Predicate> list = new ArrayList<>();//创建条件空list
            //TODO 有问题
            if (ids!=null){
                if (ids.size()<=0){
                    ids.add("-1");
                }
                List<Long> idsL = ids.stream().map(Long::valueOf).collect(Collectors.toList());
                if (StringUtils.isEmpty(rec)){//回收站标识不存在,查询不在回收站表的member
                    list.add(cb.not(id.in(idsL)));
                }else {
                    list.add(cb.isTrue(id.in(idsL)));
                }
            }
            if (!StringUtils.isEmpty(movieName)){//名字不为空则模糊查询名字
                String exc = "%" + movieName + "%";
                list.add(cb.like(root.get("commentForMovie"),exc));
            }
            return cb.and(list.toArray(new Predicate[list.size()]));//将list中的条件转为数组
        };
    }
}
