package lee.controller;

import lee.common.Code;
import lee.common.ReturnObject;
import lee.domain.Member;
import lee.service.MemberService;
import lee.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author leon
 * @date 2018-06-11 13:27
 * @desc 会员
 */
@Controller
@RequestMapping("member")
public class MemberController {
    @Autowired
    MemberService memberService;
    /**
     * 会员列表页面
     * @return
     */
    @RequestMapping("memberList")
    public ModelAndView memberList(HttpServletRequest request, Pageable pageable){
        List<Member> list = memberService.members(pageable);
        Long count = memberService.countMembers();
        ModelAndView modelAndView = new ModelAndView("/admin/member-list");
        modelAndView.addObject("memebers",list);
        modelAndView.addObject("count",count);
        return modelAndView;
    }

    /**
     * 会员增加接口
     * @return
     */
    @RequestMapping("memberAdd")
    @ResponseBody
    public ReturnObject memberAdd(HttpServletRequest request,@Param("email")String email,@Param("username")String username,@Param("password")String password) throws Exception {
        Member member = new Member();
        member.setEmail(email);
        member.setName(username);
        member.setPassword(MD5Utils.encode(password));
        member.setStatus("启用");
        Boolean result = memberService.saveMember(member);
        if (result){
            return ReturnObject.re(Code.OK,"添加成功",member);
        }else {
            return ReturnObject.re(Code.FAIL,"添加失败",member);
        }
    }

    /**
     * 会员编辑页面
     */
    @RequestMapping("memberEdit")
    public ModelAndView memberEdit(HttpServletRequest request, @Param("id")String id){
        ModelAndView modelAndView = new ModelAndView("/admin/member-edit");
        return modelAndView;
    }
}
