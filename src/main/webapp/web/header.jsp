<%@ page language="java" pageEncoding="UTF-8"%>
<!-- Header Start -->
<div id="header" style="background-color:#385e86;">
  <div class="rays">
      <div class="inner">
          <!-- Logo Start -->
          <div class="logo">
              <a href="${pageContext.request.contextPath}/web/articleAction!index.action"><img src="images/logo.png" alt="" width="250" height="42" /></a>
            </div>
            <!-- Logo End -->
            <div class="right">
                <!-- Navigation Start -->
                <div class="navigation">
                  <div id="smoothmenu1" class="ddsmoothmenu">
                        <ul>
                            <li ${typeNo == 0 ? "class='current-menu-item'" : ""}><a href="${pageContext.request.contextPath}/web/articleAction!index.action">首页</a></li>
                            <li ${typeNo == 1 ? "class='current-menu-item'" : ""}><a href="${pageContext.request.contextPath}/web/articleAction!newslist.action">新闻中心</a></li>
                            <li ${typeNo == 2 ? "class='current-menu-item'" : ""}><a href="#">驰程分校</a>
                                <ul class="backcolr">
                                    <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=5d4f6ade-ddad-4194-a037-e5050e927ca3">百色驰程驾校</a></li>
                                    <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=ad157ada-076e-4da7-9874-87e7823d2297">田林永生驾校</a></li>
                                    <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=295eaf1d-5026-4648-8469-57a70d898ffa">德保常顺驾校</a></li>
                                    <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=39b87ca8-c789-4bc9-8cdf-52ba5864d1a2">田阳威龙驾校</a></li>
                                    <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=e6509b2f-62eb-4d08-a1ea-698d485a9889">西林旺程驾校</a></li>
                                </ul>
                            </li>
                            <li ${typeNo == 3 ? "class='current-menu-item'" : ""}><a href="">集团介绍</a>
                                <ul class="backcolr">
                                    <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=9769dae9-e05e-43ca-afb4-0bb81d57506f">董事长致辞</a></li>
                                    <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=73052ec1-d11c-43a0-a172-3af792ad327a">集团概况</a></li>
                                    <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=ba10c3a8-7da3-420a-8a58-a7cafd75a8b9">企业文化</a></li>
                                    <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=ac80b145-3af6-453c-b141-eb639ab3736e">组织结构</a></li>
                                    <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=32276797-3ad2-4e82-b6bb-a96d41269870">人才招聘</a></li>
                                </ul>
                            </li>
                            <li ${typeNo == 4 ? "class='current-menu-item'" : ""}><a href="#">为您服务</a>
                                <ul class="backcolr">
                                    <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=36c04467-6bd4-439f-8abf-d8525140f951">班型价格</a></li>
                                    <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=a8de1e7a-d769-4c29-a83c-7345a84c793c">学车指南</a></li>
                                    <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=a545c6a3-9acc-4bb2-a144-df41e778b1ef">报名地点</a></li>
                                </ul>
                            </li>
                            <li ${typeNo == 5 ? "class='current-menu-item'" : ""}><a href="${pageContext.request.contextPath}/web/articleAction!buildinglist.action">党团建设</a></li>
                            <li ${typeNo == 6 ? "class='current-menu-item'" : ""}><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=9980795c-892e-4dec-bfb2-df162a46382b">联系我们</a></li>
                        </ul>
                        <div class="clear"></div>
                    </div>
                </div>
                <!-- Navigation End -->
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<!-- Header End -->