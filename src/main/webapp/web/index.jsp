<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<!--// Stylesheets //-->
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="alternate stylesheet" type="text/css" href="css/red.css" title="styles1" media="screen" />
<link rel="stylesheet" type="text/css" href="css/blue.css" title="styles2" media="screen" />
<link rel="alternate stylesheet" type="text/css" href="css/gray.css" title="styles3" media="screen" />
<link rel="alternate stylesheet" type="text/css" href="css/orange.css" title="styles4" media="screen" />
<link rel="alternate stylesheet" type="text/css" href="css/green.css" title="styles5" media="screen" />
<link rel="alternate stylesheet" type="text/css" href="css/orange-red.css" title="styles6" media="screen" />
<!--// Javascripts //-->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/styleswitch.js"></script>
<script type="text/javascript" src="js/animatedcollapse.js"></script>
<script type="text/javascript" src="js/ddsmoothmenu.js"></script>
<script type="text/javascript" src="js/jquery.nivo.slider.js"></script>
<script type="text/javascript" src="js/jquery.infinite-carousel.js"></script>
<script type="text/javascript" src="js/scrolltopcontrol.js"></script>
<script type="text/javascript" src="js/functions.js"></script>
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
    <!-- Banner Start -->
    <div style="border-color: #333;">
    <div id="banner" style="width:1100px;margin-left:40px;">
      <div class="slider-wrapper theme-default">
            <div id="slider" class="nivoSlider">
                <a href="#"><img src="images/amt1.jpg" title="#banner1" alt="" width="1100" height="390" /></a>
                <a href="#"><img src="images/amt2.jpg" title="#banner2" alt="" width="1100" height="390" /></a>
                <a href="#"><img src="images/amt3.jpg" title="#banner3" alt="" width="1100" height="390" /></a>
            </div>
            <!-- Banner Caption Start -->
            <div id="banner1" class="nivo-html-caption">
                <div class="ban-capt-desc">
                    <div class="ban-capt-mid">
                        <div class="ban-capt-in">
                            <h1 class="white">"酒后，醉酒驾车的法律处罚"</h1>
                            <p>
                              根据《中华人民共和国道路交通安全法》第九十一条规定:第九十一条饮酒后驾驶机动车的,处暂扣六个月机动车驾驶证,并处一千元以上二千元以下罚款。。。。
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Banner Caption End -->
            <!-- Banner Caption Start -->
            <div id="banner2" class="nivo-html-caption">
                <div class="ban-capt-desc">
                    <div class="ban-capt-mid">
                        <div class="ban-capt-in">
                            <h1 class="white">"广西驰程交通驾校"</h1>
                            <p>
                              学校师资力量雄厚，教学设施齐全，信誉好,口碑好,为社会各界人士提供专业、系统的机动车驾驶技能培训。施教,廉洁的培训,让您学有所成,称心满意。
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Banner Caption End -->
            <!-- Banner Caption Start -->
            <div id="banner3" class="nivo-html-caption">
                <div class="ban-capt-desc">
                    <div class="ban-capt-mid">
                        <div class="ban-capt-in">
                            <h1 class="white">"为您带来不一样的学车体验！"</h1>
                            <p>
                              优秀的教学环境，庞大的师资队伍，充足的教学资源。为您带来不一样的学车体验！
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Banner Caption End -->
        </div>
    </div>
    </div>
    <!-- Banner End -->
    <!-- Content Section Start -->
    <div id="contentsec">
      <div class="inner">
          <!-- Fullwidth Box Start -->
          <div class="fullwidth box left">
              <div class="widgets-area-conts">
                  <!-- Widget PlaceHolder Start -->
                  <div class="widget-placeholder">
                      <!-- Text Widget Start -->
                      <div class="widget textwidget">
                          <h3 class="widget-head">驾校概况</h3>
                            <h4 class="colr">　　驰程交通驾校是一家专业的机动车驾驶员培训机构。学校凭借“以人为本，服务第一”的教学宗旨“让学员完全满意”的经营理念,“管理最规范，交通最便捷，施教最廉洁，体制最正规”的高质量教学技术和良好的配套服务，深受学员的广大好评，在社会上树立良好的形象。学校师资力量雄厚，教学设施齐全，信誉好,口碑好,为社会各界人士提供专业、系统的机动车驾驶技能培训。施教,廉洁的培训,让您学有所成,称心满意。</h4>
                            <div class="clear"></div>
                        </div>
                        <!-- Text Widget End -->
                    </div>
                    <!-- Widget PlaceHolder End -->
                    <!-- Widget PlaceHolder Start -->
                    <div class="widget-placeholder">
                      <!-- Calendar Widget Start -->
                      <div class="widget wp-calendar">
                        <table>
                          <tr>
                            <td><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=36c04467-6bd4-439f-8abf-d8525140f951"><img src="images/home_18.jpg" alt="..." width="160" height="95" /></a></td>
                            <td><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=ba10c3a8-7da3-420a-8a58-a7cafd75a8b9"><img src="images/home_19.jpg" alt="..." width="160" height="95"  /></a></td>
                          </tr>
                          <tr>
                            <td><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=a545c6a3-9acc-4bb2-a144-df41e778b1ef"><img src="images/home_20.jpg" alt="..." width="160" height="95"  /></a></td>
                            <td><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=a8de1e7a-d769-4c29-a83c-7345a84c793c"><img src="images/home_17.jpg" alt="..." width="160" height="95" /></a></td>
                          </tr>
                          <tr>
                            <td><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=36c04467-6bd4-439f-8abf-d8525140f951"><img src="images/home_23.jpg" alt="..." width="160" height="95" /></a></td>
                            <td><a href="${pageContext.request.contextPath}/demo/index.jsp"><img src="images/home_22.jpg" alt="..." width="160" height="95"  /></a></td>
                          </tr>
                        </table>
                      </div>
                      <!-- Calendar Widget End -->
                    </div>
                    <!-- Widget PlaceHolder End -->
                    <!-- Widget PlaceHolder Start -->
                    <div class="widget-placeholder">
                      <!-- Donation Widget Start -->
                      <div class="widget donation">
                          <h3 class="widget-head">最新动态</h3>
                          <a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=39f8d9d0-58ff-4d11-b182-452a9169f43b"><h4 class="heading colr">黄天国局长到城东客运中心检查工作</h4></a>
                          <a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=4ff0f268-d5b3-4a82-93dd-a88f1bfa6b88"><h4 class="heading colr">集团举行2015年迎春团拜会</h4></a>
                          <a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=20feb868-64d0-4051-92fd-8d9d70b9ee27"><h4 class="heading colr">集团党委召开纪念建党94周年庆祝大会</h4></a>
                          <a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=2c551c63-561b-4fa6-9801-e4df2be84a82"><h4 class="heading colr">永安群联党支部主题教育活动</h4></a>
                          <a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=13cced2e-6a65-4f5e-87c1-49746d4885d8"><h4 class="heading colr">李贤书记慰问党员</h4></a>
                          <a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=f7575270-3a51-4835-95da-6ab735d2bc59"><h4 class="heading colr">集团党委召开“七一”党课暨“两学一做”动员会</h4></a>
                        </div>
                        <!-- Calendar Widget End -->
                    </div>
                    <!-- Widget PlaceHolder End -->
                </div>
            </div>
            <!-- Fullwidth Box End -->
            <!-- Footer Scroller Start -->
            <div class="fullwidth left">
              <img src="http://www.gxccjt.com/Datafiles/Ads/7_Info/20150122111557_291.gif" alt="" width="1100" height="115" />
            </div>
            <!-- Footer Scroller End -->
            <!-- Two Third Box Start -->
            <div class="twothird box padbox left" style="width:770px;">
              <!-- Half Box Start -->
              <div class="halfbox left">
                  <h3 class="heading colr">黄天国局长到城东客运中心检查工作</h3>
                    <!-- Home Page Post Start -->
                    <div class="home-post">
                        <a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=39f8d9d0-58ff-4d11-b182-452a9169f43b" class="thumb"><img src="http://www.gxccjt.com/datafiles/image/20150309/20150309033511_15572.jpg" alt="" width="326" height="184" /></a>
                        <h4>
                          <a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=39f8d9d0-58ff-4d11-b182-452a9169f43b">
                          2月11日，自治区道路运输管理局局长黄天国，在百色市交通运输局副局长杨华富、市道运输管理处处长杨维全和集团董事长谢宝宝宁、副董事长郑文等领导的陪同下到城东客运中心检查2015年春运工作。
                          </a>
                        </h4>
                        <a href="${pageContext.request.contextPath}/web/articleAction!newslist.action" class="readmore">Read more</a>
                    </div>
                    <!-- Home Page Post End -->
                    <h3 class="heading colr">国家公路运输枢纽百色城东客运中心隆重开业</h3>
                    <!-- Home Page Post Start -->
                    <div class="home-post">
                        <a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=36d9ba73-c958-4c4d-b8c5-9d2e6974f555" class="thumb"><img src="http://www.gxccjt.com/datafiles/image/20150309/20150309032612_61622.jpg" alt="" width="326" height="184" /></a>
                        <h4>
                          <a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=36d9ba73-c958-4c4d-b8c5-9d2e6974f555">开春咋暖，扬帆启航！2月8日，国家公路运输枢纽百色城东客运中心隆重开业！上 午九点二十八分，开业庆典仪式简朴地进行。</a>
                        </h4>
                        <a href="${pageContext.request.contextPath}/web/articleAction!newslist.action" class="readmore">Read more</a>
                    </div>
                    <!-- Home Page Post End -->
                </div>
                <!-- Half Box End -->
                <!-- Half Box Start -->
                <div class="halfbox right">
                  <h3 class="heading colr">集团党委召开纪念建党94周年庆祝大会</h3>
                    <!-- Home Page Post Start -->
                    <div class="home-post">
                        <h4><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=20feb868-64d0-4051-92fd-8d9d70b9ee27">6月30日，集团党委在金都大酒店大型会议室召开纪念中国共产党建94周年庆祝大会。集团党委书记谢宝宁、党委委员黄尚宁、李贤出席，集团三季度生产经营会、安全生产工作会全体与会人员（非党员除外）、直属党支部部分党员代表、离退休党员代表、入党积极分子共150多人参加会议，会议由党委书记谢宝宁主持。</a></h4>
                        <a href="${pageContext.request.contextPath}/web/articleAction!buildinglist.action" class="readmore">Read more</a>
                    </div>
                    <!-- Home Page Post End -->
                    <!-- Home Page Post Start -->
                    <div class="home-post">
                      <h3 class="heading colr">集团举行2015年迎春团拜会</h3>
                        <a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=4ff0f268-d5b3-4a82-93dd-a88f1bfa6b88" class="thumb"><img src="http://www.gxccjt.com/datafiles/image/20150309/20150309063038_54502.jpg" alt="" width="326" height="184" /></a>
                        <h4><a href="${pageContext.request.contextPath}/web/articleAction!info.action?id=4ff0f268-d5b3-4a82-93dd-a88f1bfa6b88">在金都大酒店四楼大型会议室举行2015年新春团拜会。集团领导、驰程汽客公司、客运公司领导、职能部门领导、各分子公司领导、集团先进员工、职工代表、离退休员工代表共175人欢聚一堂，喜迎新春。集团董事长谢宝宁致辞，副董事长郑文主持。</a></h4>
                        <a href="${pageContext.request.contextPath}/web/articleAction!newslist.action" class="readmore">Read more</a>
                    </div>
                    <!-- Home Page Post End -->
                    
                </div>
                <!-- Half Box End -->
            </div>
            <!-- Two Third Box End -->
            <!-- One Third Box Start -->
            <div class="onethird right">
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
            <!-- Footer Scroller Start -->
            <div class="fullwidth left scroll-sec">
              <a id="logoPrevious">Previous</a>
              <div id="logoscroll" class="scroller">
                  <ul>
                      <li><a href="#"><img src="images/logo1.gif" alt="" /></a></li>
                        <li><a href="#"><img src="images/logo2.gif" alt="" /></a></li>
                        <li><a href="#"><img src="images/logo3.gif" alt="" /></a></li>
                        <li><a href="#"><img src="images/logo4.gif" alt="" /></a></li>
                        <li><a href="#"><img src="images/logo5.gif" alt="" /></a></li>
                        <li><a href="#"><img src="images/logo6.gif" alt="" /></a></li>
                    </ul>
                </div>
                <a id="logoNext">Next</a>
            </div>
            <!-- Footer Scroller End -->
        </div>
    </div>
    <!-- Content Section End -->
    <!-- Footer Start -->
    <jsp:include page="footer.jsp"></jsp:include>
    <!-- Footer End -->
</div>
<!-- Wrapper End -->
<script>
	wWidth = $(window).width()
	marginLeft = (wWidth - 1100) / 2;
	console.log(marginLeft);
	$('#banner').css("marginLeft", marginLeft);

	$(window).resize(function(){
   		wWidth = $(window).width()
		marginLeft = (wWidth - 1100) / 2;
		console.log(marginLeft);
		$('#banner').css("marginLeft", marginLeft);
	});
</script>
</body>
</html>