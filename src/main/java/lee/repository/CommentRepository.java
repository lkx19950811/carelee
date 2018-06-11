package lee.repository;

import lee.domain.Comments;
import org.springframework.data.jpa.repository.Query;

/**
 * @author leon
 * @date 2018-06-11 11:58
 * @desc 评论仓库
 */
public interface CommentRepository extends Repository<Comments> {
}
