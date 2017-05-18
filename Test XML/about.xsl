<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:Waterball="http://g9.xml.csie.mcu.edu.tw">
	<xsl:template  match="/">
		<html>
			<head>
        <title>銘傳找飯吃 ? TriMaGo</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="triMago.css"/>
        <style>
          #leftSelection {
          min-height: 0px;
          padding-bottom: 15px;
          }
          p{
            font-size: 18px;
            font-family: 微軟雅黑體,微軟正黑體,新細明體,標楷體;
          }
        </style>

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
        <a class="navbar-brand" href="#" id="logo">
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
      <div class="col-sm-10 text-left">
        <div class="container" id="aboutArticlePanel">
          <div class="jumbotron ">
            <h1>銘傳大學期末製作專案</h1>
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
      <div class="col-sm-2 sidenav">
        <div class="well">
          <p>ADS</p>
        </div>
        <div class="well">
          <p>ADS</p>
        </div>
        <div class="well">
          <p>ADS</p>
        </div>
        <div class="well">
          <p>ADS</p>
        </div>
        <div class="well">
          <p>ADS</p>
        </div>
        <div class="well">
          <p>ADS</p>
        </div>
      </div>
    </div>
  </div>

  <footer class="container-fluid text-center">
    <p>WaterBall @Copy MingChang Homework-Purpose</p>
  </footer>

  
  
  </xsl:template>
	

</xsl:stylesheet>