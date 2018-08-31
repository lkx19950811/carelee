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
        <form class="layui-form layui-col-md12 x-so">
          <div class="layui-input-inline">
            <select name="sortType">
              <option value="">排序方式</option>
              <option value="commentId">按ID</option>
              <option value="commentVote">点赞数</option>
            </select>
          </div>
          <div class="layui-input-inline">
            <select name="order">
              <option value="">排序方法</option>
              <option value="asc">升序</option>
              <option value="desc">降序</option>
            </select>
          </div>
          <input type="text" name="movieName"  placeholder="请输入电影名" autocomplete="off" class="layui-input" value="${params.movieName}">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <xblock>
          <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
          <span class="x-right" style="line-height:40px">共有数据：${comments.totalElements} 条,      排序方法:${params.sort}</span>
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
            <td>${commnet.commentAuthorImgUrl}</td>
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
            <td class="td-manage">
              <a title="查看"  onclick="x_admin_show('查看','https://www.baidu.com/s?wd=${commnet.commentForMovie}')" href="javascript:;">
                <i class="layui-icon">&#xe63c;</i>
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
        <%--<div>--%>
          <%--<c:if test="${(currPage)!=0}">--%>
            <%--<a class="prev" href="/comment/list?sort=${params.sort.toString()}&size=${params.size}&page=${currPage-1}&movieName=${params.movieName}&start=${params.start}&end=${params.end}">&lt;&lt;</a>--%>
          <%--</c:if>--%>
          <%--<c:forEach var="i" begin="0" end="${(comments.totalPages-1)<0?0:comments.totalPages-1}">--%>
            <%--<c:choose>--%>
              <%--<c:when test="${i==currPage}">--%>
                <%--<span class="current">${i+1}</span>--%>
              <%--</c:when>--%>
              <%--<c:when test="${i<10 + currPage && i!=currPage}">--%>
                <%--<a class="num" href="/comment/list?sort=${params.sort.toString()}&size=${params.size}&page=${i}&movieName=${params.movieName}&start=${params.start}&end=${params.end}">${i+1}</a>--%>
              <%--</c:when>--%>
              <%--<c:when test="${i!=currPage && i==comments.totalPages-1 && i-currPage>1}">--%>
                <%--..........--%>
                <%--<a class="num" href="/comment/list?sort=${params.sort.toString()}&size=${params.size}&page=${i}&movieName=${params.movieName}&start=${params.start}&end=${params.end}">${i+1}</a>--%>
              <%--</c:when>--%>
            <%--</c:choose>--%>
          <%--</c:forEach>--%>
          <%--<c:if test="${(currPage+1)!=page.totalPages}">--%>
            <%--<a class="next" href="/comment/list?sort=${params.sort.toString()}&size=${params.size}&page=${currPage+1}&movieName=${params.movieName}&start=${params.start}&end=${params.end}">&gt;&gt;</a>--%>
          <%--</c:if>--%>
        <%--</div>--%>
      </div>

    </div>
    <script>
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
            var form = layui.form
            // //自定义验证规则
            // form.verify({
            //     nikename: function(value){
            //         if(value.length < 5){
            //             return '昵称至少得5个字符啊';
            //         }
            //     }
            //     ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            //     ,repass: function(value){
            //         if($('#L_pass').val()!=$('#L_repass').val()){
            //             return '两次密码不一致';
            //         }
            //     }
            // });

            //监听提交
            form.on('submit(sreach)', function(data){
                //发异步，把数据提交给php
                var param = data.field
                param.sort = "commentId"
                if (param.sortType){
                    param.sort = param.sortType
                }
                if (param.order){
                    param.sort = param.sort + ',' + param.order
                }else {
                    param.sort = param.sort + ',desc'
                }
                console.log(param)
                location.href = '/comment/list?sort=' + param.sort  +'&movieName=' + param.movieName + '&rec=' + '&size=${params.size}'
                return false;//如果不加false,就提交表单了
            });
        });
    /*分页*/
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
                    location.href = '/comment/list?sort=${params.sort.toString()}&size=' + obj.limit + '&page=' + (obj.curr-1) +'&movieName=${params.movieName}'
                }

            }});
        })

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
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              $.post('/comment/deltoRecComment',{"ids":[id]},function (res) {
                  if (res.code=='OK'){
                      $(obj).parents("tr").remove();
                      layer.msg('已删除!可以在回收站中找回',{icon:1,time:1000});
                  }else {
                      layer.msg('删除失败!',{icon:2,time:1000});
                  }
              })

          });
      }
        function delAll () {
            var data = tableCheck.getData();
            console.log(data)
            layer.confirm('确认要删除吗？删除的用户可以在回收站找回',function(index){
                //捉到所有被选中的，发异步进行删除
                $.post("/comment/deltoRecComment",{"ids":data},function (res) {
                    if (res.code=="OK"){
                        layer.msg(res.message, {icon: 1});
                        $(".layui-form-checked").not('.header').parents('tr').remove();
                    }else {
                        layer.msg(res.message, {icon: 1});
                    }
                },"json")

            });
        }
    </script>
  </body>

</html>