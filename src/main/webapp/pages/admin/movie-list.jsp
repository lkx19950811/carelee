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
        <form class="layui-form layui-col-md12 x-so" method="post" action="/movie/list?&size=${pagesize}">
            <div class="layui-input-inline">
                <select name="sort">
                    <option value="">排序方式</option>
                    <option value="ratingNum,asc">按评分升序</option>
                    <option value="ratingNum,desc">按评分降序</option>
                    <option value="movieId,desc">按爬取时间降序</option>
                  <option value="movieId,desc">按爬取时间升序</option>
                </select>
            </div>
          <input type="text" name="movieName"  placeholder="请输入电影名" autocomplete="off" class="layui-input" value="${movieName}">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      <xblock>
          <span class="x-right" style="line-height:40px">共有数据：${movies.totalElements} 条</span>
      </xblock>
      <table class="layui-table">
        <thead>
        <tr>
          <th>电影名称</th>
          <th>国家</th>
          <th>导演</th>
          <th>语言</th>
          <th>评分</th>
          <th>上映时间</th>
          <th>标签</th>
          <th>操作</th>
        </tr>
        </thead>
        <c:forEach var="movie" items="${movies.content}">
          <tbody>
          <tr>
            <td>${movie.name}</td>
            <td>${movie.country=='null'?'未知':movie.country}</td>
            <td>${movie.director}</td>
            <td>${movie.language}</td>
            <td>${movie.ratingNum}</td>
            <td>${movie.releaseDate=='null'?'未知':movie.releaseDate}</td>
            <c:if test="${movie.tags.length()>6}">
                <td title="${movie.tags}">${movie.tags.substring(0,5)}......</td>
            </c:if>
            <c:if test="${movie.tags.length()<=6}">
                <td>${movie.tags}......</td>
            </c:if>
            <td class="td-manage">
              <a title="查看"  onclick="x_admin_show('查看','https://www.baidu.com/s?wd=${movie.name}')" href="javascript:;">
                <i class="layui-icon">&#xe63c;</i>
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

            <%--//监听提交--%>
            <%--form.on('submit(sreach)', function(data){--%>
                <%--//发异步，把数据提交给php--%>
                <%--var param = data.field--%>
                <%--location.href = '/comment/list?sort=' + param.sort  +'&movieName=' + param.movieName + '&rec=' + '&size=${params.size}'--%>
                <%--return false;//如果不加false,就提交表单了--%>
            <%--});--%>
        });
    /*分页*/
    layui.use('laypage', function(){
        var laypage = layui.laypage;
        //执行一个laypage实例
        laypage.render({
            elem: 'page' //注意，这里的  是 ID，不用加 # 号
            ,count: ${movies.totalElements} //数据总数，从服务端得到
            ,limit:${pagesize}
            ,curr:${movies.number + 1}//服务器返回的页数从0开始,所以要加一
            ,layout:['prev', 'page', 'next','count','skip']
            ,jump: function(obj, first){
                //obj包含了当前分页的所有参数，比如：
                // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。该页数从1开始,向服务器请求需要-1
                // console.log(obj.limit); //得到每页显示的条数
                //首次不执行
                if(!first){
                    location.href = '/movie/list?size=' + obj.limit + '&page=' + (obj.curr-1) +'&movieName=${movieName}&sort=${sort}'
                }

            }});
        })

    </script>
  </body>

</html>