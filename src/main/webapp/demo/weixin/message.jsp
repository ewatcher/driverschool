<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!doctype html>
<html class="no-js fixed-layout">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>消息</title>
<meta name="description" content="这是一个 index 页面" />
<meta name="keywords" content="index" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="renderer" content="webkit" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="amaze/assets/i/favicon.png" />
<link rel="apple-touch-icon-precomposed"
	href="amaze/assets/i/app-icon72x72@2x.png" />
<meta name="apple-mobile-web-app-title" content="Amaze UI" />

</head>
<body>

	<link rel="stylesheet" href="amaze/assets/css/amazeui.min.css" />
	<div class="doc-example">
		<ul class="am-comments-list am-comments-list-flip">
			<li class="am-comment">
				<a href="#link-to-user-home">
					<img src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/96/h/96" alt="" class="am-comment-avatar" width="48" height="48">
				</a>
				<div class="am-comment-main">
					<header class="am-comment-hd">
						<div class="am-comment-meta">
							<a href="#link-to-user" class="am-comment-author">某人</a> 评论于
							<time datetime="2013-07-27T04:54:29-07:00"
								title="2013年7月27日 下午7:54 格林尼治标准时间+0800">2014-7-12 15:30</time>
						</div>
					</header>
					<div class="am-comment-bd">
						<p>那，那是一封写给南部母亲的信。我茫然站在骑楼下，我又看到永远的樱子走到街心。其实雨下得并不大，却是一生一世中最大的一场雨。而那封信是这样写的，年轻的樱子知不知道呢？</p>
						<blockquote>妈：我打算在下个月和樱子结婚。</blockquote>
					</div>
				</div>
			</li>
			<li class="am-comment"><a href="#link-to-user-home"><img
					src="http://www.gravatar.com/avatar/1ecedeede84a44f371b9d8d656bb4265?d=mm&amp;s=96"
					alt="" class="am-comment-avatar" width="48" height="48"></a>
			<div class="am-comment-main">
					<header class="am-comment-hd">
						<div class="am-comment-meta">
							<a href="#link-to-user" class="am-comment-author">路人甲</a> 评论于
							<time datetime="2013-07-27T04:54:29-07:00"
								title="2013年7月27日 下午7:54 格林尼治标准时间+0800">2014-7-13 0:03</time>
						</div>
					</header>
					<div class="am-comment-bd">
						<p>
							She's gone 我早知道<br>她将要 从我的生命中走掉<br>不再停留多一秒
						</p>
					</div>
				</div>
			</li>
			<li class="am-comment am-comment-primary">
				<a href="#link-to-user-home">
					<img src="http://www.gravatar.com/avatar/1ecedeede84abbf371b9d8d656bb4265?d=mm&amp;s=96" alt="" class="am-comment-avatar" width="48" height="48">
				</a>
			<div class="am-comment-main">
					<header class="am-comment-hd">
						<div class="am-comment-meta">
							<a href="#link-to-user" class="am-comment-author">路人乙</a> 评论于
							<time datetime="2013-07-27T04:54:29-07:00"
								title="2013年7月27日 下午7:54 格林尼治标准时间+0800">2014-7-14 23:30</time>
						</div>
					</header>
					<div class="am-comment-bd">
						<p>
							<a href="#lin-to-user">@某人</a> 撸主保重！
						</p>
					</div>
				</div></li>
			<li class="am-comment am-comment-flip am-comment-secondary"><a
				href="#link-to-user-home"><img
					src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/96/h/96"
					alt="" class="am-comment-avatar" width="48" height="48"></a>
			<div class="am-comment-main">
					<header class="am-comment-hd">
						<div class="am-comment-meta">
							<a href="#link-to-user" class="am-comment-author">某人</a> 评论于
							<time datetime="2013-07-27T04:54:29-07:00"
								title="2013年7月27日 下午7:54 格林尼治标准时间+0800">2014-7-14 23:301</time>
						</div>
					</header>
					<div class="am-comment-bd">
						<p>
							<a href="#lurenyi">@路人乙</a> 朕知道了！
						</p>
					</div>
				</div></li>
			<li class="am-comment am-comment-highlight"><a
				href="#link-to-user-home"><img
					src="http://www.gravatar.com/avatar/1ecedeede84abbf371b9d8d656bb4265?d=mm&amp;s=96"
					alt="" class="am-comment-avatar" width="48" height="48"></a>
			<div class="am-comment-main">
					<header class="am-comment-hd">
						<div class="am-comment-meta">
							<a href="#link-to-user" class="am-comment-author">路人乙</a> 评论于
							<time datetime="2013-07-27T04:54:29-07:00"
								title="2013年7月27日 下午7:54 格林尼治标准时间+0800">2014-7-14 23:32</time>
						</div>
					</header>
					<div class="am-comment-bd">
						<p>
							<a href="#lin-to-user">@某人</a> 艹民告退！
						</p>
					</div>
				</div></li>
			<li class="am-comment am-comment-flip am-comment-danger"><a
				href="#link-to-user-home"><img
					src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/96/h/96"
					alt="" class="am-comment-avatar" width="48" height="48"></a>
			<div class="am-comment-main">
					<header class="am-comment-hd">
						<div class="am-comment-meta">
							<a href="#link-to-user" class="am-comment-author">某人</a> 评论于
							<time datetime="2013-07-27T04:54:29-07:00"
								title="2013年7月27日 下午7:54 格林尼治标准时间+0800">2014-7-14 23:301</time>
						</div>
					</header>
					<div class="am-comment-bd">
						<p>
							<a href="#lurenyi">@路人乙</a> 滚！
						</p>
					</div>
				</div></li>
			<li class="am-comment am-comment-warning"><a
				href="#link-to-user-home"><img
					src="http://www.gravatar.com/avatar/1ecedeede84abbf371b9d8d656bb4265?d=mm&amp;s=96"
					alt="" class="am-comment-avatar" width="48" height="48"></a>
			<div class="am-comment-main">
					<header class="am-comment-hd">
						<div class="am-comment-meta">
							<a href="#link-to-user" class="am-comment-author">路人乙</a> 评论于
							<time datetime="2013-07-27T04:54:29-07:00"
								title="2013年7月27日 下午7:54 格林尼治标准时间+0800">2014-7-14 23:32</time>
						</div>
					</header>
					<div class="am-comment-bd">
						<p>
							<a href="#lin-to-user">@某人</a> 你妹！
						</p>
					</div>
				</div></li>
			<li class="am-comment am-comment-flip am-comment-success"><a
				href="#link-to-user-home"><img
					src="http://s.amazeui.org/media/i/demos/bw-2014-06-19.jpg?imageView/1/w/96/h/96"
					alt="" class="am-comment-avatar" width="48" height="48"></a>
			<div class="am-comment-main">
					<header class="am-comment-hd">
						<div class="am-comment-meta">
							<a href="#link-to-user" class="am-comment-author">某人</a> 评论于
							<time datetime="2013-07-27T04:54:29-07:00"
								title="2013年7月27日 下午7:54 格林尼治标准时间+0800">2014-7-14 23:301</time>
						</div>
					</header>
					<div class="am-comment-bd">
						<p>
							<a href="#lurenyi">@路人乙</a> 你妹你妹！
						</p>
					</div>
				</div></li>
		</ul>
	</div>
</body>
</html>
