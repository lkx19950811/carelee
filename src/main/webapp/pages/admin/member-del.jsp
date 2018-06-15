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
  
  <body>
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
        <form method="post" class="layui-form layui-col-md12 x-so" action="/member/memberList?page=0&size=${params.size}&sort=createdDate,desc&rec=yes">
          <input class="layui-input" placeholder="开始日" name="start" id="start" value="${param.start}">
          <input class="layui-input" placeholder="截止日" name="end" id="end" value="${param.end}">
          <input type="text" name="name"  placeholder="请输入用户名" autocomplete="off" class="layui-input" value="${param.name}">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量恢复</button>
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
              <span class="layui-btn layui-btn-danger layui-btn-mini">
                                已删除
              </span></td>
            <td class="td-manage">
              <a title="恢复" onclick="member_rec(this,${member.id})" href="javascript:;">
                <i class="layui-icon">&#xe618;</i>
              </a>
              <a title="删除" onclick="member_del(this,${member.id})" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
      <div id="page">

      </div>

    </div>
    <script>
        layui.use('laypage', function(){
            var laypage = layui.laypage;
            //执行一个laypage实例
            laypage.render({
                elem: 'page' //注意，这里的 page 是 ID，不用加 # 号
                ,count: ${count} //数据总数，从服务端得到
                ,limit:${params.size}//每页条数
                ,curr:${currPage+1}//服务器返回的页数从0开始
                ,layout:['prev', 'page', 'next','count','skip']//自定义模块
                ,jump: function(obj, first){//点击跳转的回调函数
                    //obj包含了当前分页的所有参数，比如：
                    // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。该页数从1开始
                    // console.log(obj.limit); //得到每页显示的条数
                    //首次不执行
                    if(!first){
                        location.href = '/member/memberList?page='+ (obj.curr-1) + '&size=${params.size}&sort=createdDate,desc&rec=yes&start=${params.start}&end=${params.end}'
                    }
                }});
        });
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

      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要彻底删除吗？',function(index){
              $.post("/member/delMember",{"id":id},function (res) {
                  if (res.code=='OK'){
                      $(obj).parents("tr").remove();
                      layer.msg('已删除!',{icon:1,time:1000});
                  }else {
                      layer.msg('删除失败!',{icon:2,time:1000});
                  }
              })
          });
      }
      /*用户恢复*/
      function member_rec(obj,id){
          layer.confirm('确认要恢复吗？',function(index){
              //发异步删除数据
              $.post("/member/recoveryMembers",{"ids":[id]},function (res) {
                  if (res.code=='OK'){
                      $(obj).parents("tr").remove();
                      layer.msg('已恢复!',{icon:1,time:1000});
                  }else {
                      layer.msg('恢复失败!',{icon:2,time:1000});
                  }
              })
          });
      }


      function delAll (argument) {

        var data = tableCheck.getData();
  
        layer.confirm('确认要恢复吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            $.post("/member/recoveryMembers",{"ids":data},function (res) {
                if (res.code=='OK'){
                    layer.msg('恢复成功!恢复条数: ' + res.object, {icon: 1});
                    $(".layui-form-checked").not('.header').parents('tr').remove();
                }else {
                    layer.msg('恢复失败', {icon: 2});
                }
            })
        });
      }
    </script>
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>
  </body>

</html>