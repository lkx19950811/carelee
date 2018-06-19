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
        <i class="layui-icon" style="line-height:30px">&#xe9aa;</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form method="post" class="layui-form layui-col-md12 x-so" action="/comment/commentDel?page=0&size=${params.size}&sort=commentId,desc&rec=yes">
            <input type="text" name="movieName"  placeholder="请输入电影名" autocomplete="off" class="layui-input" value="${param.movieName}">
            <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量恢复</button>
        <span class="x-right" style="line-height:40px">共有数据：${comments.totalElements}条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>评论编号</th>
            <th>昵称</th>
            <th>评论地址</th>
            <th>电影名</th>
            <th>评论内容</th>
            <th>点赞数</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <c:forEach var="commnet" items="${comments.content}">
            <tbody>
            <tr>
                <td>
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${commnet.commentId}'><i class="layui-icon">&#xe605;</i></div>
                </td>
                <td>${commnet.commentId}</td>
                <td>${commnet.commentAuthor}</td>
                <td>${commnet.commentAuthorUrl}</td>
                <td>${commnet.commentForMovie}</td>
                <c:choose>
                    <c:when test="${commnet.commentInfo.length()>40}">
                        <td title="${commnet.commentInfo}">${commnet.commentInfo.substring(0,40)}...........</td>
                    </c:when>
                    <c:otherwise>
                        <td>${commnet.commentInfo}</td>
                    </c:otherwise>
                </c:choose>
                <td>${commnet.commentVote}</td>
                <td class="td-status">
              <span class="layui-btn layui-btn-danger layui-btn-mini">已删除
              </span></td>
                <td class="td-manage">
                    <a title="恢复" onclick="member_rec(this,${commnet.commentId})" href="javascript:;">
                        <i class="layui-icon">&#xe618;</i>
                    </a>
                    <a title="删除" onclick="member_del(this,${commnet.commentId})" href="javascript:;">
                        <i class="layui-icon">&#xe640;</i>
                    </a>
                </td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
    <div id="page">

    </div>

</div>
<script>
    layui.use('laypage', function(){
        var laypage = layui.laypage;
        //执行一个laypage实例
        laypage.render({
            elem: 'page' //注意，这里的  是 ID，不用加 # 号
            ,count: ${comments.totalElements} //数据总数，从服务端得到
            ,limit:${params.size}
            ,curr:${currPage+1}//服务器返回的页数从0开始,所以要加一
            ,layout:['prev', 'page', 'next','count','skip']
            ,jump: function(obj, first){
                //obj包含了当前分页的所有参数，比如：
                // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。该页数从1开始,向服务器请求需要-1
                // console.log(obj.limit); //得到每页显示的条数
                //首次不执行
                if(!first){
                    location.href = '/comment/list?sort=commentId,desc&size=' + obj.limit + '&page=' + (obj.curr-1) +'&movieName=${params.movieName}'
                }

            }});
    });

    /*用户-删除*/
    function member_del(obj,id){
        layer.confirm('确认要彻底删除吗？',function(index){
            $.post("/comment/delComment",{"id":id},function (res) {
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
            $.post("/comment/recComment",{"ids":[id]},function (res) {
                if (res.code=='OK'){
                    $(obj).parents("tr").remove();
                    layer.msg('已恢复!',{icon:1,time:1000});
                }else {
                    layer.msg('恢复失败!',{icon:2,time:1000});
                }
            })
        });
    }

    /**
     * 批量恢复
     * @param argument
     */
    function delAll (argument) {
        var data = tableCheck.getData();
        layer.confirm('确认要恢复吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            console.log(data)
            $.post("/comment/recComment",{"ids":data},function (res) {
                if (res.code=='OK'){
                    layer.msg('恢复成功!恢复条数: ' + res.object, {icon: 1});
                    $(".layui-form-checked").not('.header').parents('tr').remove();
                }else {
                    layer.msg('恢复失败', {icon: 2});
                }
            },"json")
        });
    }
</script>
</body>
</html>