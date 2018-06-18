package lee.service;

import lee.domain.Comments;
import lee.domain.spec.CommentSpec;
import lee.repository.CommentRepository;
import lee.repository.RecycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author leon
 * @date 2018-06-11 12:00
 * @desc 评论服务类
 */
@Service
public class CommentsService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    RecycleRepository recycleRepository;
    /**
     * 评论计数
     * @return
     */
    public Long CountComment(){
        return commentRepository.count();
    }

    /**
     * 评论分页
     * @param pageable
     * @return
     */
    public Page<Comments> comments(Pageable pageable,String movieName,String rec){
        List<String> ids = recycleRepository.commentIds();
        Specification<Comments> specification = CommentSpec.findComments(movieName,ids,rec);
        Page<Comments> comments = commentRepository.findAll(specification,pageable);
        return comments;
    }
    public void delComment(Long id){
        commentRepository.delete(id);
    }

}
