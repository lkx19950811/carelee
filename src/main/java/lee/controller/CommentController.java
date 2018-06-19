package lee.controller;

import lee.common.Code;
import lee.common.ReturnObject;
import lee.domain.Comments;
import lee.domain.Recycle;
import lee.repository.RecycleRepository;
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
    @Autowired
    RecycleRepository recycleRepository;
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
     * 讲评论放入回收站中
     * @param ids
     * @return
     */
    @RequestMapping("deltoRecComment")
    public ReturnObject deltoRecComment(@RequestParam("ids[]") String[] ids){
        int result = commentsService.putRec(ids);
        if (result>0){
            return ReturnObject.re(Code.OK,"已放入回收站中");
        }else {
            return ReturnObject.re(Code.FAIL,"删除失败!");
        }
    }
    /**
     * 删除单条评论
     * @param id
     * @return
     */
    @RequestMapping("delComment")
    public ReturnObject delComment(@RequestParam Long id){
        commentsService.delComment(id);
        return ReturnObject.re(Code.OK,"删除成功");
    }

    /**
     * 得到评论回收站内容
     * @param movieName
     * @param pageable
     * @param rec
     * @return
     */
    @RequestMapping("commentDel")
    public ModelAndView commentDel(@RequestParam(required = false) String movieName,Pageable pageable,@RequestParam(required = false) String rec){
        ModelAndView modelAndView = new ModelAndView("/admin/comment-del");
        Map params = new HashMap();
        params.put("size",pageable.getPageSize());
        params.put("movieName",movieName);
        Page<Comments> comments = commentsService.comments(pageable,movieName,rec);
        modelAndView.addObject("comments",comments);
        modelAndView.addObject("currPage",pageable.getPageNumber());
        modelAndView.addObject("params",params);
        return modelAndView;
    }

    /**
     * 从回收站恢复评论
     * @param ids
     * @return
     */
    @RequestMapping("recComment")
    public ReturnObject recComment(@RequestParam("ids[]") String[] ids){
        int result = recycleRepository.deleteCommentByIds(ids);
        if (result>0){
            return ReturnObject.re(Code.OK,"恢复成功",result);
        }else {
            return ReturnObject.re(Code.FAIL,"恢复失败");
        }
    }
}
