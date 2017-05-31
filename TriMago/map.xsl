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
        <style>
          #map{
          height: 600px;
          width: 100%;
          }
          #leftSelection{
          min-height: 900px;
          padding-bottom: 15px;
          }
			.cse .gsc-search-button input.gsc-search-button-v2,
			input.gsc-search-button-v2 {
				height: 26px !important;
				margin-top: 0 !important;
				min-width: 13px !important;
				padding: 5px 26px !important;
				width: 68px !important;
			}
			#select-btn{
				width: 220px;
			}
        </style>

      </head>
			<body>
					<xsl:apply-templates select="Waterball:WebSite"/>
			</body>
		</html>
	</xsl:template>
  
  <xsl:template match="Waterball:WebSite">

    <nav class="navbar navbar-default"  >
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
          <form action="map" method="get" id="searchPanelForm">
                 <input id="typeOfMealInput" name="typeOfMeal" type="hidden"/>
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
                        <div class="col-sm-7">
                          <!--讓點擊分類之後能夠將文字印到按鈕上-->
                          <script>
                            $(function(){

                            $(".search-dropdown-menu li a").click(function(){

                            $("#select-btn:first-child").text($(this).text());
                            $("#select-btn:first-child").val($(this).text());
                            $("#typeOfMealInput:first-child").val($(this).text());
                            });

                            });
                          </script>
                          
                            <div class="dropdown" style="float:right; width:180px;">
                                <input value ="選擇分類" class="btn btn-primary dropdown-toggle" id="select-btn" type="button" data-toggle="dropdown"/>
        
                                <ul class="dropdown-menu search-dropdown-menu">
                                    <li><a href="#">無分類</a></li>
									<li><a href="#">早午餐店</a></li>
									<li><a href="#">小吃店、路邊攤</a></li>
									<li><a href="#">甜點、冰</a></li>
									<li><a href="#">正餐、麵、飯</a></li>
									<li><a href="#">下午茶、咖啡廳</a></li>
									<li><a href="#">速食店</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
              </form>
                <hr/>
          <!--地圖從這加-->
          <div id="map" style="width:100% ; height:600px;"></div>
		  <xsl:variable name="id" select="@id"/>
          <script>
	
            function attachSecretMessage(marker,href,id) {
            var innerhtml = "<h3>"+marker.get('title')+"</h3>"
            + "&#60;form action='" + href + "'&#62;"
            + "&#60;input type='submit' class='btn btn-success' value='查看詳情' /'&#62;"
			+ "&#60;input type='hidden' name='id' value="+id+" /'&#62;"
            + "&#60;/form	&#62;";

            var infowindow = new google.maps.InfoWindow({
            content: innerhtml
            });

            marker.addListener('click', function() {
            infowindow.open(marker.get('map'), marker);
            });
            }
            function initMap() {
            var myLatlng = {lat: 24.985859, lng: 121.343826};
            var mapOptions = {
            zoom: 15,
            center: myLatlng
            }
            var map = new google.maps.Map(document.getElementById("map"), mapOptions);
            <xsl:for-each select="Waterball:restaurant">
                attachSecretMessage(new google.maps.Marker({
                    position: {lat: <xsl:value-of select="@latitude" /> , lng: <xsl:value-of select="@longitude" />},
                    title:"<xsl:value-of select="@name" />",
                  map: map
                } )
                , "detail" , "<xsl:value-of select="@id"/>");
              </xsl:for-each>
            }

            
          </script>
          <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDdaJtSkjptq9XngwoRWm_QOL_UczaKb_I&amp;callback=initMap"></script>
        </div>
        <div class="col-sm-2 sidenav">
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
						  <h4><xsl:value-of select="../@name"/></h4>
						</div>
				</div>
		</xsl:if>
  </xsl:template>

</xsl:stylesheet>