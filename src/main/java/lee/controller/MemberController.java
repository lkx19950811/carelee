package lee.controller;

import lee.common.Code;
import lee.common.ReturnObject;
import lee.domain.Member;
import lee.service.MemberService;
import lee.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * @author leon
 * @date 2018-06-11 13:27
 * @desc 会员
 */
@RestController
@RequestMapping("member")
public class MemberController {
    private Logger Logger = LoggerFactory.getLogger(getClass());
    @Autowired
    MemberService memberService;
    /**
     * 会员列表页面
     * @return
     */
    @RequestMapping("memberList")
    public ModelAndView memberList(HttpServletRequest request, Pageable pageable){
        Page<Member> members = memberService.members(pageable);
        Long count = memberService.countMembers();
        ModelAndView modelAndView = new ModelAndView("/admin/member-list");
        modelAndView.addObject("members",members.getContent());
        modelAndView.addObject("count",members.getTotalElements());
        modelAndView.addObject("currPage",pageable.getPageNumber());
        modelAndView.addObject("page",members);
        modelAndView.addObject("size",pageable.getPageSize());
        return modelAndView;
    }

    /**
     * 会员增加/修改接口
     * @return
     */
    @RequestMapping(value = "memberAdd",method = RequestMethod.POST)
    public ReturnObject memberAdd(HttpServletRequest request,Member member,@RequestParam(required = false) Long id) throws Exception {
        Member result = new Member();
        member.setPassword(MD5Utils.encode(member.getPassword()));
        if (id!=null){//如果已经有id则去数据库查出
            Optional<Member> optionalMember = memberService.findMemberById(id);
            if (optionalMember.isPresent()){
                result = optionalMember.get();
                result.setName(member.getName());
                result.setPassword(member.getPassword());
                result.setEmail(member.getEmail());
                result.setName(member.getName());
            }
        }else {//如果没有id,则新建Member
            if (member.getStatus()==null){
                member.setStatus("启用");
                result = member;
            }
        }
        Optional<Member> res = memberService.saveMember(result);
        if (res.isPresent()){
            return ReturnObject.re(Code.OK,"成功",res.get());
        }else {
            return ReturnObject.re(Code.FAIL,"失败",result);
        }
    }

    /**
     * 会员编辑页面
     * @param request
     * @param id 会员id
     * @return
     */
    @RequestMapping("memberEdit")
    public ModelAndView memberEdit(HttpServletRequest request, @Param("id")Long id){
        Optional<Member> member = memberService.findMemberById(id);
        ModelAndView modelAndView = new ModelAndView("/admin/member-edit");
        if (member.isPresent()){
            modelAndView.addObject("member",member.get());
        }
        return modelAndView;
    }
    /**
     * 修改会员状态接口
     */
    @RequestMapping("modStatus")
    public ReturnObject modStatus(Long id){
        Optional<Member> memberop = memberService.findMemberById(id);
        if (memberop.isPresent()){
            Member member = memberop.get();
            String status = member.getStatus();
            switch (status){
                case "启用":
                    member.setStatus("停用");
                    break;
                case "停用":
                    member.setStatus("启用");
                    break;
            }
            memberop = memberService.saveMember(member);
        }
        if (memberop.isPresent()){
            return ReturnObject.re(Code.OK,"修改状态成功",memberop.get());
        }else {
            return ReturnObject.re(Code.FAIL,"修改状态失败",null);
        }
    }

    /**
     * 修改密码页面
     * @param id
     * @return
     */
    @RequestMapping("modPass")
    public ModelAndView modPass(@Param("id") Long id){
        ModelAndView modelAndView = new ModelAndView("/admin/member-password");
        Optional<Member> optionalMember = memberService.findMemberById(id);
        if (optionalMember.isPresent()){
            modelAndView.addObject("member",optionalMember.get());
        }
        return modelAndView;
    }

    /**
     * 修改密码接口
     * @param id
     * @param oldpass
     * @param newpass
     * @return
     * @throws Exception
     */
    @RequestMapping("modifyPass")
    public ReturnObject modifyPass(@Param("id")Long id,@Param("oldpass")String oldpass,@Param("newpass")String newpass) throws Exception {
        Optional<Member> optionalMember = memberService.findMemberById(id);
        if (optionalMember.isPresent()){
            Member member = optionalMember.get();
            if (MD5Utils.verify(oldpass,member.getPassword())){//验证密码
                member.setPassword(MD5Utils.encode(newpass));
            }else{
                return ReturnObject.re(Code.FAIL,"旧密码不正确",null);
            }
        }else {
            return ReturnObject.re(Code.ERROR,"未知异常,请联系管理员",null);
        }
        return ReturnObject.re(Code.OK,"修改密码成功",null);
    }

    @RequestMapping("delMember")
    public ReturnObject delMember(@RequestParam Long id){
        memberService.delByid(id);
        return ReturnObject.re(Code.OK,"删除成功",null);
    }
}
