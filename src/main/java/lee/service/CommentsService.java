package lee.service;

import lee.domain.Comments;
import lee.domain.Member;
import lee.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Comments> comments(Pageable pageable){
        Page<Comments> comments = commentRepository.findAll(pageable);
        return comments;
    }

}
