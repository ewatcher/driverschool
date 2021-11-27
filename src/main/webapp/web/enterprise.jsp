<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title} 驰程驾校</title>
<!--// Stylesheets //-->
<link href="../web/css/style.css" rel="stylesheet" type="text/css" />
<link rel="alternate stylesheet" type="text/css" href="../web/css/red.css" title="styles1" media="screen" />
<link rel="stylesheet" type="text/css" href="../web/css/blue.css" title="styles2" media="screen" />
<link rel="alternate stylesheet" type="text/css" href="../web/css/gray.css" title="styles3" media="screen" />
<link rel="alternate stylesheet" type="text/css" href="../web/css/orange.css" title="styles4" media="screen" />
<link rel="alternate stylesheet" type="text/css" href="../web/css/green.css" title="styles5" media="screen" />
<link rel="alternate stylesheet" type="text/css" href="../web/css/orange-red.css" title="styles6" media="screen" />
<link href="../web/css/fancybox.css" rel="stylesheet" type="text/css" />
<!--// Javascripts //-->
<script type="text/javascript" src="../web/js/jquery.min.js"></script>
<script type="text/javascript" src="../web/js/styleswitch.js"></script>
<script type="text/javascript" src="../web/js/animatedcollapse.js"></script>
<script type="text/javascript" src="../web/js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="../web/js/scrolltopcontrol.js"></script>
<script type="text/javascript" src="../web/js/tabs.js"></script>
<script type="text/javascript" src="../web/js/jquery.fancybox-1.3.1.js"></script>
<script type="text/javascript" src="../web/js/functions.js"></script>
<link rel="stylesheet" href="../ueditor/themes/iframe.css" />
<script src="../ueditor/ueditor.parse.js"></script>
</head>

<body>
<!-- Wrapper Start -->
<div id="outer_wrapper">
  <!-- Top Bar Start -->
    <div id="top-bar" class="backcolr">
      <div class="inner">
          <!-- Styleswitcher Start -->
            <div class="styles">
                <ul>
                    <li><a href="blog.html?style=style1" rel="styles1" class="styleswitch red">&nbsp;</a></li>
                    <li><a href="blog.html?style=style2" rel="styles2" class="styleswitch blue">&nbsp;</a></li>
                    <li><a href="blog.html?style=style3" rel="styles3" class="styleswitch gray">&nbsp;</a></li>
                    <li><a href="blog.html?style=style4" rel="styles4" class="styleswitch orange">&nbsp;</a></li>
                    <li><a href="blog.html?style=style5" rel="styles5" class="styleswitch green">&nbsp;</a></li>
                    <li><a href="blog.html?style=style6" rel="styles6" class="styleswitch orange-red">&nbsp;</a></li>
                </ul>
            </div>
            <!-- Styleswitcher End -->
            <!-- Top Bar Text Start -->
            <p class="text"></p>
            <!-- Top Bar Text End -->
        </div>
        <div class="clear"></div>
    </div>
    <!-- Top Bar End -->
    <!-- Header Start -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- Header End -->
    <!-- Bread Crumb Start -->
    <div id="bread-crumb">
      <div class="inner">
          <ul>
                <li style="margin:-3px -10px 0px 5px;"><a href="#">首页</a></li>
                <li style="margin:-3px -10px 0px 5px;"><a href="#">集团介绍</a></li>
                <li style="margin:-3px -10px 0px 5px;"><a href="#">${title}</a></li>
            </ul>
            <div class="clear"></div>
        </div>
    </div>
    <!-- Banner End -->
    <!-- Content Section Start -->
    <div id="contentsec">
      <div class="inner">
          <!-- Fullwidth Box Start -->
          <div class="fullwidth box left">
              <div class="subpage-banner">
                  <a href="#"><img src="images/ad1.jpg" alt="" width="1092" height="215" /></a>
                </div>
            </div>
            <!-- Fullwidth Box Start -->
          <!-- Two Third Box Start -->
            <div class="twothird box left"  style="width:810px; padding:20px;">
              <h2 class="widget-head-border colr">${title}</h2>
                <div id="content" style="letter-spacing:4px; line-height:150%;">
                    ${content}
                </div>
            </div>
            <!-- Two Third Box End -->
            <!-- One Third Box Start -->
            <div class="onethird right" style="width:280px;">
              
                
                <!-- One Third Box Start -->
                <div class="onethird box left" style="width:100%;">
                  <h3 class="widget-head-border colr">集团介绍</h3>
                  <!-- Blog Categories Widget Start -->
                <div class="widget widget_categories">
                      <ul>
                          <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=9769dae9-e05e-43ca-afb4-0bb81d57506f">董事长致辞</a></li>
                          <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=73052ec1-d11c-43a0-a172-3af792ad327a">集团概况</a></li>
                          <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=ba10c3a8-7da3-420a-8a58-a7cafd75a8b9">企业文化</a></li>
                          <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=ac80b145-3af6-453c-b141-eb639ab3736e">组织结构</a></li>
                          <li><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=32276797-3ad2-4e82-b6bb-a96d41269870">人才招聘</a></li>
                        </ul>
                    </div>
                    <!-- Blog Categories Widget Start -->
                </div>
                <!-- One Third Box End -->
                <!-- One Third Box Start -->
                <div class="onethird box left" style="width:100%;">
                  <!-- Facebook Widget Start -->
                  <div class="widget facebook-widget" style="text-align:center;">
                      <img src="images/weixin.jpg" alt="" width="230" height="230" />
                      <h4 class="colr">微信公众号直接练车预约！</h4>
                  </div>
                    <!-- Facebook Widget Start -->
                </div>
                <!-- One Third Box End -->
                <!-- One Third Box Start -->
                <div class="onethird box left" style="width:100%;">
                  <h3 class="widget-head-border colr">友情链接</h3>
                  <!-- Blog Categories Widget Start -->
                    <div class="widget widget_categories">
                        <div class="tags-box">
                      	  <a href="http://www.jxedt.com/" target="_blank">驾校一点通</a>
                          <a href="http://www.gazx.gov.cn/gxgat/gkbmcqjdcwjcq.jhtml" target="_blank">机动车违章查询</a>
                          <a href="http://www.gxccjt.com/" target="_blank">广西驰程汽车运输集团有限公司</a>
                          <a href="http://www.jsyks.com/kmy-fzks" target="_blank">仿真考场（科目一）</a>
                          <a href="http://www.jsyks.com/kmy-fzks" target="_blank">仿真考场（科目四）</a>
                        </div>
                    </div>
                    <!-- Blog Categories Widget Start -->
                </div>
                <!-- One Third Box End -->
            </div>
            <!-- One Third Box End -->
        </div>
    </div>
    <!-- Content Section End -->
    <!-- Footer Start -->
    <jsp:include page="footer.jsp"></jsp:include>
    <!-- Footer End -->
</div>
<!-- Wrapper End -->

</body>
</html>