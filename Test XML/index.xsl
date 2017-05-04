<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:Waterball="http://g9.xml.csie.mcu.edu.tw">
	<xsl:template  match="/">
		<html>
			<head>
				<title>銘傳找飯吃? TriMaGo</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="triMago.css"/>
			</head>
			<body>
					<xsl:apply-templates select="Waterball:WebSite"/>
			</body>
		</html>
	</xsl:template>
  
  <xsl:template match="Waterball:WebSite">
      
    <nav class="navbar navbar-default" data-spy="affix" data-offset-top="130" id="nav">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#" id="logo">
                    <img src="https://imgur.com/download/8WZa7jf" alt="logo"/>
                </a>
            </div>
            <nav class="nav nav-pills" id="myNavbar">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#" id="homepage" class="navigationRight" > 首頁</a></li>
                    <li><a href="AboutUs.html" id="about" class="navigationRight" > 關於我們</a></li>
                </ul>
            </nav>
        </div>
    </nav>

    <div class="container-fluid" id="leftSelection">
        <div class="row content">
            <div class="col-sm-10 text-left">
                <div class="container" id="searchPanel">
                    <div class="row" id="searchBar">
                        <input class="flipkart-navbar-input col-xs-11" placeholder="輸入想查詢的餐廳名稱" name="searchInput" />
                        <button class="flipkart-navbar-button col-xs-1">
                            <svg width="15px" height="15px">
                                <path d="M11.618 9.897l4.224 4.212c.092.09.1.23.02.312l-1.464 1.46c-.08.08-.222.072-.314-.02L9.868 11.66M6.486 10.9c-2.42 0-4.38-1.955-4.38-4.367 0-2.413 1.96-4.37 4.38-4.37s4.38 1.957 4.38 4.37c0 2.412-1.96 4.368-4.38 4.368m0-10.834C2.904.066 0 2.96 0 6.533 0 10.105 2.904 13 6.486 13s6.487-2.895 6.487-6.467c0-3.572-2.905-6.467-6.487-6.467 "></path>
                            </svg>
                        </button>
                    </div>
                    <div class="row" id="mealTypeMenu" >
                        <div class="col-sm-9">
                            <div class="dropdown">
                                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                                    選擇分類
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">早餐</a></li>
                                    <li><a href="#">午餐</a></li>
                                    <li><a href="#">早午餐</a></li>
                                    <li><a href="#">下午茶</a></li>
                                    <li><a href="#">晚餐</a></li>
                                    <li><a href="#">宵夜</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <a href="MapPage.html" id="goToGoogleMap">前往美食地圖→</a>
                        </div>
                    </div>
                </div>
                <hr/>

                <xsl:apply-templates select="Waterball:restaurant"/>

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
	
	<xsl:template  match="Waterball:restaurant">
    <div class="media" id="restaurantList">
      <div class="col-sm-8">
        <div class="media-left">
          <xsl:variable name="imageUrl" select="@imageUrl"/>
          <img src="{$imageUrl}" class="media-object" style="width:250px"/>
        </div>
        <div class="media-body">
          <h1 class="media-heading"> <xsl:value-of select="@name"/> </h1>
          <div class="item__stars-wrp">
            <!--計算該餐廳的評價 = 所有留言給予的評價之平均-->
            <xsl:variable name="rate" select="sum(Waterball:comment/@rate) div count(Waterball:comment)"/>
            <xsl:for-each select="(//node())[$rate >= position()]">
              <span class="icon-ic icon-category/star item__star"><svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" viewBox="0 0 12 12"><path class="svg-color--primary" fill="#F6AB3F" d="M11.988 5.21c-.052-.275-.27-.488-.545-.534l-3.604-.6L6.63.455C6.542.184 6.287 0 6 0s-.542.184-.632.456L4.16 4.076l-3.603.6c-.275.046-.493.26-.545.533-.052.273.072.55.312.695L3.2 7.63l-1.165 3.493c-.093.28.01.59.25.758.115.08.25.12.382.12.148 0 .295-.05.416-.146L6 9.52l2.917 2.333c.12.098.27.147.416.147.133 0 .267-.04.38-.12.244-.17.346-.478.252-.758L8.8 7.63l2.876-1.725c.24-.144.364-.422.312-.696z"></path></svg></span>
            </xsl:for-each>
          </div>
          <p class="address"><xsl:value-of select="@address"/></p>
          <p class="price">價位 <xsl:value-of select="@price"/></p>
        </div>
      </div>
      <div class="col-sm-4" style="padding-top:111px">
        <button type="button" class="btn btn-success detailsButton">查看詳情</button>
      </div>
     </div>
	</xsl:template>

</xsl:stylesheet>