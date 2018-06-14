package lee.controller;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import lee.domain.Comments;
import lee.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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
    public ModelAndView comments(HttpServletRequest request, Pageable pageable, @RequestParam(required = false) String start,@RequestParam(required = false) String end,@RequestParam(required = false) String movieName){
        Page<Comments> comments = commentsService.comments(pageable);
        Map params = new HashMap();
        String sort = pageable.getSort().toString();
        sort = sort.split(":")[0] + "," + sort.split(":")[1].trim().toLowerCase();
        params.put("sort",sort);
        params.put("size",pageable.getPageSize());
        params.put("movieName",movieName);
        params.put("start",start);
        params.put("end",end);
        ModelAndView modelAndView = new ModelAndView("/admin/comment-list");
        modelAndView.addObject("comments",comments);
        modelAndView.addObject("currPage",pageable.getPageNumber());
        modelAndView.addObject("params",params);
        return modelAndView;
    }
}
