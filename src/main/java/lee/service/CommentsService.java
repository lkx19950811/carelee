package lee.service;

import lee.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author leon
 * @date 2018-06-11 12:00
 * @desc 评论服务类
 */
@Service
public class CommentsService {
    @Autowired
    CommentRepository commentRepository;
    public Long CountComment(){
        return commentRepository.count();
    }
}
