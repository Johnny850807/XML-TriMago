<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:Waterball="http://g9.xml.csie.mcu.edu.tw">
	<xsl:template  match="/">
		<html>
			<head>
        <title>銘傳找飯吃 ? TriMaGo</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
		<meta name="description" content="銘傳找飯吃? TriMago! 一個無須登入可自由分享以及評價的銘傳周邊美食網站，歡迎分享你的美食經驗！"/>
		<meta name="keywords" content="銘傳大學,美食,trimago"/>
		<meta name="author" content="潘冠辰"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="triMago.css"/>
		<script src="https://apis.google.com/js/platform.js" async="true" defer="true">
				  {lang: 'zh-TW'}
				</script>
        <style>
          #leftSelection {
          min-height: 0px;
          padding-bottom: 15px;
          }
          p{
            font-size: 18px;
            font-family: 微軟雅黑體,微軟正黑體,新細明體,標楷體;
          }
		  .cse .gsc-search-button input.gsc-search-button-v2,
			input.gsc-search-button-v2 {
				height: 26px !important;
				margin-top: 0 !important;
				min-width: 13px !important;
				padding: 5px 26px !important;
				width: 68px !important;
			}
        </style>
		<script>
		  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

		  ga('create', 'UA-100403751-1', 'auto');
		  ga('send', 'pageview');

		</script>

      </head>
			<body>
					<xsl:apply-templates select="Waterball:WebSite"/>
			</body>
		</html>
	</xsl:template>
  
<xsl:template match="Waterball:WebSite"  >
  <nav class="navbar navbar-default" data-spy="affix" data-offset-top="130" id="nav">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="index" id="logo">
          <img src="https://imgur.com/download/8WZa7jf" alt="logo"/>
        </a>
      </div>
      <nav class="nav nav-pills" id="myNavbar">
        <ul class="nav navbar-nav navbar-right">
          <li>
            <a href="index" id="homepage" class="navigationRight"> 首頁</a>
          </li>
          <li>
            <a href="about" id="about" class="navigationRight"> 關於我們</a>
          </li>
        </ul>
      </nav>
    </div>
  </nav>

  <div class="container-fluid" id="leftSelection">
    <div class="row content">
      <div class="col-sm-9 text-left">
        <div class="container" id="aboutArticlePanel">
          <div class="jumbotron ">
            <h1>銘傳找飯吃? TriMago，銘傳美食</h1>
			<div class="g-plusone" data-annotation="inline" data-width="300"></div>
          </div>
          <br/>
          <p>
            網站原型為 <a href="https://www.trivago.com.tw/">trivago</a>。
            感謝豐緒老師指導，利用 XML 以及 JavaServlet 製作出簡單的動態網頁。
          </p>
          <p>
            創作動機為大學生似乎永遠都不知道午餐吃什麼，在銘傳要找飯吃 ? 那就
            triMago 囉 。<br />trivago 的口號在此時朗朗上口並且流為廣傳，因此就以此做為網頁設計目標了。
          </p> <hr/>
          <p>
            使用到的API包括 Imgur API , Google Map API 以及自己製作的 DataHandler 處理 XML 資料， <br/>
            原始碼都在<a href="https://github.com/Johnny850807/XML-TriMago">Github XML-TriMago / Johnny850807</a> 中。
          </p>
        </div>
        <p style="color:deepskyblue ; float:right ; font-size:35px ; margin-top:800px ; font-family: 微軟正黑體 " >水球潘</p>
      </div>
      <div class="col-sm-3 sidenav">
		<script>
				  (function() {
					var cx = '016452915066670004559:xkj40c_fjrw';
					var gcse = document.createElement('script');
					gcse.type = 'text/javascript';
					gcse.async = true;
					gcse.src = 'https://cse.google.com/cse.js?cx=' + cx;
					var s = document.getElementsByTagName('script')[0];
					s.parentNode.insertBefore(gcse, s);
				  })();
		</script>
		<div class="gcse-search"></div>
        <div id="myCarousel" class="carousel slide" data-ride="carousel" style="margin-top:20px;margin-bottom:20px;">
						<div class="carousel-inner">
							<xsl:apply-templates select="Waterball:restaurant/@imageUrl" />
						</div>
						<a class="left carousel-control" href="#myCarousel" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="right carousel-control" href="#myCarousel" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right"></span>
							<span class="sr-only">Next</span>
						</a>
				</div>
        <div class="well">
                    <audio src="music3.mp3" controls="controls" autoplay="true" style="width:100%;"></audio>
                </div>
				<div class="well">
					<h2><kbd>2017/6/1 2:31</kbd></h2> 
					<h2><kbd>TeamPathy</kbd></h2> 
					<h2 style="display:inline;"><kbd>專研APP強勢上架</kbd></h2>
                    <img src="adv1.png" style="width:40px;height:40px;"/>
					<iframe src="teampathy.mp4" style="width:100%;height:200px;margin-top:15px;margin-bottom:15px;" allowfullscreen=""></iframe>
					<h3><kbd>盡請期待 另外徵求APP高手</kbd></h3>
                </div>
			
      </div>
    </div>
  </div>

  <footer class="container-fluid text-center">
    <p>WaterBall @Copy MingChang Homework-Purpose</p>
  </footer>

  
  
  </xsl:template>
	
	<xsl:template match="Waterball:restaurant/@imageUrl">
		<xsl:if test="position() > 1">
				<div class="item">
						<a href="detail?id={../@id}"><img src="{../@imageUrl}" alt="{../@name}" style="width:100%;"/></a>
						<div class="carousel-caption">
						  <h2><xsl:value-of select="../@name"/></h2>
						</div>
				</div>
		</xsl:if>
		<xsl:if test="position() = 1">
				<div class="item active">
						<a href="detail?id={../@id}"><img src="{../@imageUrl}" alt="{../@name}" style="width:100%;"/></a>
						<div class="carousel-caption">
						  <h2><xsl:value-of select="../@name"/></h2>
						</div>
				</div>
		</xsl:if>
  </xsl:template>

</xsl:stylesheet>