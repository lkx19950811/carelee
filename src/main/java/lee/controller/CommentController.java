package lee.controller;

import lee.common.Code;
import lee.common.ReturnObject;
import lee.domain.Comments;
import lee.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leon
 * @date 2018-06-14 13:55
 * @desc
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    CommentsService commentsService;
    @RequestMapping("list")
    public ModelAndView comments(HttpServletRequest request, Pageable pageable, @RequestParam(required = false) String movieName,
                                 @RequestParam(required = false)String rec){
        Page<Comments> comments = commentsService.comments(pageable,movieName,rec);
        Map params = new HashMap();
        String sort = pageable.getSort().toString();
        sort = sort.split(":")[0] + "," + sort.split(":")[1].trim().toLowerCase();
        params.put("sort",sort);
        params.put("size",pageable.getPageSize());
        params.put("movieName",movieName);
        ModelAndView modelAndView = new ModelAndView("/admin/comment-list");
        modelAndView.addObject("comments",comments);
        modelAndView.addObject("currPage",pageable.getPageNumber());
        modelAndView.addObject("params",params);
        return modelAndView;
    }

    /**
     * 删除单条评论
     * @param id
     * @return
     */
    @RequestMapping("delComment")
    public ReturnObject delComment(Long id){
        commentsService.delComment(id);
        return ReturnObject.re(Code.OK,"删除成功");
    }
}
