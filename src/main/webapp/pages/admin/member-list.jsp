<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="/css/font.css">
    <link rel="stylesheet" href="/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="../lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body class="layui-anim layui-anim-up">
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" action="/member/memberList?page=0&size=${params.size}&sort=createdDate,desc">
          <input class="layui-input" placeholder="开始日" name="start" id="start" value="${params.start}">
          <input class="layui-input" placeholder="截止日" name="end" id="end" value="${params.end}">
          <input type="text" name="name"  placeholder="请输入用户名" autocomplete="off" class="layui-input" value="${params.name}">
          <button type="submit" class="layui-btn"  lay-submit="" lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加用户','/iframe/member-add.html',600,400)"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：${count} 条</span>
      </xblock>
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>用户名</th>
            <th>邮箱</th>
            <th>加入时间</th>
            <th>状态</th>
            <th>操作</th></tr>
        </thead>
        <tbody>
        <c:forEach var="member" items="${members}">
          <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${member.id}'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>${member.id}</td>
            <td>${member.name}</td>
            <td>${member.email}</td>
            <td>${member.createdDate}</td>
            <td class="td-status">
              <c:choose>
                <c:when test="${member.status=='启用'}">
                <span class="layui-btn layui-btn-normal layui-btn-mini">
                    ${member.status}</span></td>
            <td class="td-manage">
              <a onclick="member_stop(this,${member.id})" href="javascript:;"  title="启用">
                <i class="layui-icon">&#xe601;</i>
                </c:when>
                <c:when test="${member.status=='停用'}">
                <span class="layui-btn layui-btn-normal layui-btn-mini layui-btn-disabled">
                    ${member.status}</span></td>
            <td class="td-manage">
              <a onclick="member_stop(this,${member.id})" href="javascript:;"  title="停用">
                <i class="layui-icon">&#xe62f;</i>
                </c:when>
              </c:choose>

              </a>
              <a title="编辑"  onclick="x_admin_show('编辑','/member/memberEdit?id=${member.id}',600,400)" href="javascript:;">
                <i class="layui-icon">&#xe642;</i>
              </a>
              <a onclick="x_admin_show('修改密码','/member/modPass?id=${member.id}',600,400)" title="修改密码" href="javascript:;">
                <i class="layui-icon">&#xe631;</i>
              </a>
              <a title="删除" onclick="member_del(this,${member.id})" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
      <div class="page">
        <div>
          <a class="prev" href="/member/memberList?page=${currPage-1==0?currPage:currPage-1}&size=${params.size}&sort=createdDate,desc&name=${params.name}&start=${params.start}&end=${params.end}">&lt;&lt;</a>
          <c:forEach var="i" begin="${0}" end="${page.totalPages-1<0?0:page.totalPages}">
            <c:choose>
              <c:when test="${i==currPage}">
                <span class="current"  onclick="javascript:void (0)">${i+1}</span>
              </c:when>
              <c:when test="${i<10 && i!=currPage && i==(page.totalPages-1)}">
                <a class="num" href="/member/memberList?page=${i}&size=${params.size}&sort=createdDate,desc&name=${params.name}&start=${params.start}&end=${params.end}">${i+1}</a>
              </c:when>
            </c:choose>
          </c:forEach>
          <a class="next" href="/member/memberList?page=${currPage+1==page.totalPages?currPage:currPage+1}&size=${params.size}&sort=createdDate,desc&name=${params.name}&start=${params.start}&end=${params.end}">&gt;&gt;</a>
        </div>
      </div>

    </div>
    <script>
      $(function () {
          
      })
      function refresh(){
          location.replace(location.href);
      }
      layui.use('laydate', function(){
        var laydate = layui.laydate;
        
        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });
      });

       /*用户-停用*/
      function member_stop(obj,id){
          layer.confirm('确认要更改状态吗？',function(index){
              //console.log(obj)
              $.post("/member/modStatus",{"id":id},function (res) {
                  if (res.code=="OK"){
                      if($(obj).attr('title')=='启用'){
                          //发异步把用户状态进行更改
                          $(obj).attr('title','停用')
                          $(obj).find('i').html('&#xe62f;');

                          $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('停用');
                          layer.msg('已停用!',{icon: 5,time:1000});

                      }else{
                          $(obj).attr('title','启用')
                          $(obj).find('i').html('&#xe601;');

                          $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('启用');
                          layer.msg('已启用!',{icon: 6,time:1000});
                      }
                  }else {
                      layer.msg('修改失败',{icon: 5,time:1000});
                  }
              })
          });
      }

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              $.post("/member/delMember",{"id":id},function (res) {
                  if (res.code=="OK"){
                      $(obj).parents("tr").remove();
                      layer.msg('已删除!',{icon:1,time:1000});
                  } else {
                      layer.msg('删除失败',{icon:2,time:1000})
                  }
              })
          });
      }



      function delAll (argument) {
        var data = tableCheck.getData();
        console.log(data)
        layer.confirm('确认要删除吗？',function(index){
            //捉到所有被选中的，发异步进行删除
            $.post("/member/delMembers",{ids:data},function (res) {
                if (res.code=="OK"){
                  layer.msg(res.message, {icon: 1});
                  $(".layui-form-checked").not('.header').parents('tr').remove();
                }else {
                  layer.msg(res.message, {icon: 1});
                }
            })

        });
      }

      <%--layui.use(['form','layer'], function(){--%>
          <%--$ = layui.jquery;--%>
          <%--var form = layui.form--%>
              <%--,layer = layui.layer;--%>

          <%--//自定义验证规则--%>
          <%--// form.verify({--%>
          <%--//     nikename: function(value){--%>
          <%--//         if(value.length < 2){--%>
          <%--//             return '昵称至少得2个字符啊';--%>
          <%--//         }--%>
          <%--//     }--%>
          <%--//     ,pass: [/(.+){6,12}$/, '密码必须6到12位']--%>
          <%--//     ,repass: function(value){--%>
          <%--//         if($('#L_pass').val()!=$('#L_repass').val()){--%>
          <%--//             return '两次密码不一致';--%>
          <%--//         }--%>
          <%--//     }--%>
          <%--// });--%>

          <%--//监听提交--%>
          <%--form.on('submit(search)', function(data){--%>
              <%--console.log(data.field);--%>
              <%--//发异步，把数据提交给php--%>
              <%--$.post("/member/memberList?page=0&size=${params.size}&sort=createdDate,desc",data.field,function (res) {--%>
                  <%--if (res.code=='OK') {--%>
                          <%--//刷新页面--%>
                          <%--refresh();--%>
                  <%--}else {--%>
                      <%--layer.alert("查询失败", {icon: 5})--%>
                  <%--}--%>
              <%--},"json");--%>
              <%--return false;--%>
          <%--});--%>
      <%--});--%>
    </script>
  </body>

</html>